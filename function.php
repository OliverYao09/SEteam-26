<?php

//Return json format data
function returnJson($errcode,$msg,$data = []){
    $res = [
        'errcode' => $errcode,
        'msg'  => $msg,
        'data' => $data
    ];
    echo json_encode($res);
    die;
}

//Generate order number
function setNumber()
{
    $osn = date('Ymd').substr(implode(NULL, array_map('ord', str_split(substr(uniqid(), 7, 13), 1))), 0, 8);
    return $osn;
}