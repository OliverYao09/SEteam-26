<?php
ini_set('date.timezone','Australia/Sydney');
header("Content-type:text/html;charset=utf-8");
include_once("function.php");
include_once("config.php");
include_once("Db.php");


try{
    $server = new Server($config);
    $server->choiceFunction();
}catch(Exception $e){
    var_dump($e->getMessage());
}

class Server{
    private $db;
    private $op;
    private $config;
    public function __construct($config){
        $this->db = new DB();
        $this->op = isset($_POST['op']) ? $_POST['op'] : 0;
        $this->config = $config;
    }

    //Choose a different method
    public function choiceFunction(){
        switch($this->op){
            case $this->op == 'submit'://submit Form
                $this->submit();
                break;
            case $this->op == 'search'://checking booking number
                $this->search();
                break;
            case $this->op == 'distribution'://Assign (modify status)
                $this->distribution();
                break;

        }
    }

    /**
     * Save order information submitted by users
     */
    private function submit(){
        //Get submission information
        $params['customer_name'] = isset($_POST['customer_name']) ? $_POST['customer_name'] : '';
        $params['phone']         = isset($_POST['phone']) ? $_POST['phone'] : '';
        $params['pickuptime']    = isset($_POST['pickuptime']) ? $_POST['pickuptime'] : '';
        $params['pickupaddress'] = isset($_POST['pickupaddress']) ? $_POST['pickupaddress'] : '';
        $params['destination']   = isset($_POST['destination']) ? $_POST['destination'] : '';

        //verify information that use entered
        $this->validateForm($params);

        //Convert time format
        $params['pickuptime'] = date('Y-m-d H:i:s',strtotime($params['pickuptime']));

        //Duplicate verification
        $this->checkRepeat($params);

        //Generate an orders
        $params['number'] = setNumber();
        //Generate order create time
        $params['create_time'] = date('Y-m-d H:i:s',time()+7200);

        //save information
        $id = $this->db->insert('orders',$params);
        if($id){
            //Find order information
            $orderInfo = $this->getOrderById($id);
            //Return order information
            $data = [
                'number'        =>  $orderInfo['number'],
                'pickupdate'    =>  date('Y-m-d',strtotime($orderInfo['pickuptime'])),
                'pickuptime'    =>  date('H:i:s',strtotime($orderInfo['pickuptime'])),
            ];
            returnJson(0,'Saved successfully!',$data);
        }
        returnJson(0,'Save failed!');
    }

    /**
     * checking order
     */
    private function search()
    {
        $number = isset($_POST['number']) ? $_POST['number'] : '';
        $type   = isset($_POST['type']) ? $_POST['type'] : 1;
        $number = trim($number);

        if($type == 1){
            //Check the specific booking number and verify parameters
            if(empty($number)){
                returnJson(1,'Booking Reference Number cannot be null!');
            }
            $sql = "select * from orders where number = '{$number}' and status='unassigned'";
        }else{
            //searching order by time range
            $before_time = $this->config['before_time'];
            $now = date('Y-m-d H:i:s',time()+7200);
            $start_time = date('Y-m-d H:i:s',strtotime($now) - ($before_time * 3600));
            $sql = "select * from orders where create_time between '{$start_time}' and '{$now}' and status='unassigned' order by id desc";
        }
        $rows = $this->db->fetchAll($sql);
        returnJson(0,'Query complete!',$rows);
    }

    /**
     * Distribute orders (modify status)
     */
    private function distribution()
    {
        $number = isset($_POST['number']) ? $_POST['number'] : '';
        if(empty($number)){
            returnJson(1,'The booking reference number cannot be null!');
        }
        $sql ="UPDATE `orders` SET `status`='assigned' WHERE `number`='{$number}'";
        $rs = $this->db->query($sql);
        if($rs){
            returnJson(0,'Assigned successfully!');
        }else{
            returnJson(0,'Assigned failed!');
        }
    }

    /**
     * Validate form data
     * @param $params
     */
    private function validateForm($params){
        //Validate lastname
        if(empty($params['customer_name'])){
            returnJson(1,'Last Name cannot be null!');
        }
        //Validate phone
        if(empty($params['phone'])){
            returnJson(1,'Phone Number cannot be null!');
        }
        //Validate pickuptime
        if(empty($params['pickuptime'])){
            returnJson(1,'Pick-up Time wrong format!');
        }
        //Verify that the pickuptime time cannot be less than the current time
        if(strtotime($params['pickuptime']) <= time()){
            returnJson(1,'Pick-up Time cannot be earlier than the current time!');
        }
        //Verify pickupaddress
        if(empty($params['pickupaddress'])){
            returnJson(1,'Pick-up Address cannot be null!');
        }
        //Verify destination
        if(empty($params['destination'])){
            returnJson(1,'Enter your Destination cannot be null!');
        }
        //Verify if it contains numbers
        $pa_num = '/\d/';
        if(preg_match($pa_num,$params['customer_name'])){
            returnJson(1,'Username cannot be a number!');
        }
        if(!preg_match($pa_num,$params['pickupaddress'])){
            returnJson(1,'Pick-up Address must be start with unit number !');
        }
        if(!preg_match($pa_num,$params['destination'])){
            returnJson(1,'Destination must be start with unit number !');
        }
        //Verify phone number format
        $pa = '/^\d{10,11}$/';
        if(!preg_match($pa,$params['phone'])){
            returnJson(1,'Phone Numer Needs to be <h5 style="color:red">10-11 digits !</h5>');
        }
    }

    /**
     * Duplicate verification
     * @param $params
     */
    private function checkRepeat($params)
    {
        $sql = "select count(1) as count from orders where customer_name='{$params['customer_name']}' and phone='{$params['phone']}' and pickuptime='{$params['pickuptime']}' and pickupaddress='{$params['pickupaddress']}' and destination='{$params['destination']}'";
        $row = $this->db->fetch($sql);
        if($row['count'] > 0){
            returnJson(1,'This appointment already exists!');
        }
    }

    /**
     * Get order information based on id
     * @param $id order id
     * @return array
     */
    private function getOrderById($id)
    {
        $sql = "select id,number,pickuptime from orders where id={$id}";
        $row = $this->db->fetch($sql);
        return $row;
    }
}