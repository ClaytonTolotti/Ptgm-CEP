#!/bin/bash

declare -a array=("TYPE=04&OUTLET=02&RFID=FFFF0002&OFFSET=2077&GAIN=430003F8&RMS=3E999B0E&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=4254A050%3BBFE0F68A%3BBE464CAA%3BBF62A529%3BBFA9CDB2%3BBDCA9270%3B3E8FB022%3BBE8987A2%3B3C6A3700%3BBEFD04EB%3BBEFF5D15%3BBE9BB644&COS=412A13F6%3BBCFBBE6C%3BBEBA6265%3BBDD75965%3BBFB807F3%3B3DFC5F62%3B3A7BB900%3BBDBB55DF%3B3E72D46A%3B3DA7D24B%3BBDBA04F4%3B3E25F431")

for request in ${array[@]};
do

curl -X POST -H "POST / HTTP/1.1\n\r Host: localhost \n\r Content-Length: 301\r\n Content-Type: application/x-www-form-urlencoded\r\n\r\n" -d "$request" http://localhost:8080/protegemed/webservice/operations/post/receive-event

sleep 2 

done
