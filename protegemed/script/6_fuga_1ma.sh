#!/bin/bash

declare -a array=("TYPE=06&OUTLET=02&RFID=FFFF0002&OFFSET=2093&GAIN=444E6B09&RMS=3C8499FC&MV=00A97661&MV2=45031550&UNDER=0000&OVER=0000&DURATION=56466&SIN=00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000&COS=00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000%3B00000000")

for request in ${array[@]};
do

curl -X POST -H "POST / HTTP/1.1\n\r Host: localhost \n\r Content-Length: 301\r\n Content-Type: application/x-www-form-urlencoded\r\n\r\n" -d "$request" http://localhost:8080/protegemed/webservice/operations/post/receive-event

sleep 2 

done
