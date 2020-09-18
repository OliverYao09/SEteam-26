<?php

class DB
{
    private $dsn;
    private $sth;
    private $dbh;
    private $user;
    private $charset;
    private $password;

    public $lastSQL = '';

    public function __construct($config = array())
    {
        if(empty($config)){
            include_once("db_config.php");
        }
        $this->dsn = "mysql:dbname={$config['dbname']};host={$config['host']}";
        $this->user = $config['username'];
        $this->password = $config['password'];
        $this->charset = $config['charset'];
        $this->connect();
    }

    private function connect()
    {
        if(!$this->dbh){
            $options = array(
                \PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES ' . $this->charset,
            );
            $this->dbh = new \PDO($this->dsn, $this->user,
                $this->password, $options);
        }
    }

    public function beginTransaction()
    {
        return $this->dbh->beginTransaction();
    }

    public function inTransaction()
    {
        return $this->dbh->inTransaction();
    }

    public function rollBack()
    {
        return $this->dbh->rollBack();
    }

    public function commit()
    {
        return $this->dbh->commit();
    }

    public function fetchAll($sql)
    {
        $result = [];
        $this->lastSQL = $sql;
        $this->sth = $this->dbh->prepare($sql);
        $this->sth->execute();
        while($result[] = $this->sth->fetch(\PDO::FETCH_ASSOC)){ }
        array_pop($result);
        return $result;
    }

    public function exists($sql)
    {
        $this->lastSQL = $sql;
        $data = $this->fetch($sql);
        return !empty($data);
    }

    public function query($sql)
    {
        $this->lastSQL = $sql;
        $this->sth = $this->dbh->prepare($sql);
        $this->sth->execute();
        return $this->sth->rowCount();
    }

    public function fetch($sql, $type=\PDO::FETCH_ASSOC)
    {
        $this->lastSQL = $sql;
        $this->sth = $this->dbh->prepare($sql);
        $this->sth->execute();
        return $this->sth->fetch($type);
    }

    public function fetchColumn($sql, $position=0)
    {
        $this->lastSQL = $sql;
        $this->sth = $this->dbh->prepare($sql);
        $this->sth->execute();
        return $this->sth->fetch(\PDO::FETCH_COLUMN, $position);
    }

    public function insert($table, $parameters=[])
    {
        $table = $this->format_table_name($table);
        $sql = "INSERT INTO $table";
        $fields = [];
        $placeholder = [];
        foreach ( $parameters as $field=>$value){
            $placeholder[] = ':'.$field;
            $fields[] = '`'.$field.'`';
        }
        $sql .= '('.implode(",", $fields).') VALUES ('.implode(",", $placeholder).')';

        $this->lastSQL = $sql;
        $this->sth = $this->dbh->prepare($sql);
        $this->sth->execute($parameters);
        $id = $this->dbh->lastInsertId();
        if(empty($id)) {
            return $this->sth->rowCount();
        } else {
            return $id;
        }
    }

    public function errorInfo()
    {
        return $this->sth->errorInfo();
    }

    protected function format_table_name($table)
    {
        $parts = explode(".", $table, 2);

        if(count($parts) > 1) {
            $table = $parts[0].".`{$parts[1]}`";
        } else {
            $table = "`$table`";
        }
        return $table;
    }

    function errorCode()
    {
        return $this->sth->errorCode();
    }
}