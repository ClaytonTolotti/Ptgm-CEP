#!/bin/bash
while IFS='' read -r line || [[ -n "$line" ]]; do
    IP=$line
done < ip.txt
#done < "$1"
echo $IP
