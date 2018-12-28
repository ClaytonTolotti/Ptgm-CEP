#README

##Introdução
No repositório há dois projetos, **protegemed** e **protegemed-receive**

* protegemed: Web Service REST + CEP(Drools)
* protegemed-receive: Web Service REST + CEP(Drools)

####O que é cada projeto
O **protegemed** é utilizado na integração com o dispositivo MBED e será integrado também ao Texas, a aplicação recebe os eventos dos dispositivos e os processa.
Exemplo de requisição do MBED:
*TYPE=01&OUTLET=01&RFID=FFFF0002&OFFSET=2093&GAIN=444E6B09&RMS=3FA277C5&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=44A74464%3BC232764E%3BC02F3D9A%3BC12780FC%3B42618EF4%3BC1CC261E%3BC21668A5%3BC0E1178A%3BC164BAB6%3BC0A07318%3BC10AEC48%3BC095ACE4&COS=C41D1918%3B418A1530%3B41B06ECC%3B40F0FE2A%3B42321A4A%3BC0C2D6BE%3BBE3880E4%3BC01D72A3%3BC12024DE%3B3FF28A7C%3BC05C0DE6%3B4014D9FD"*

O **protegemed-receive** é utilizado para reprocessamento de eventos salvos em um banco de dados, na pasta scripts-vm enconstram-se todos os scripts
montados a partir dos dados de um BD, o arquivo [request.sql](script-vm/request.sql) contém a query desenvolvida para a montagem das requisições.
Exemplo de requisição:
*TYPE=1&OUTLET=5&RFID=FFFF0005&OFFSET=2089&GAIN=768.92&RMS=0.858343&MV=538018&MV2=2101.63&UNDER=0&OVER=0&DURATION=0&SIN=117509;-9446.36;-619.203;-2889.91;-2434.17;-1846.14;-675.162;-1948.73;-1432.65;-1125.12;-1008.98;-1088.92&COS=-17331.4;3326.45;3735.97;-164.288;2288.53;-489.134;-211.598;-442.197;-569.487;58.6782;-704.785;710.063&DATE=2014-05-25;16:52:04"*

Na pasta script-vm encontra-se todos os scripts utilizados durante os testes de desenvolvimento do projeto, os scripts devem ser executados individualmente, para tanto, o arquivo [ip.txt](script-vm/ip.txt) deve conter o ip da VM onde o protegemed está em execução, os scripts farão a leitura desse arquivo, para que, caso haja a alteração de IP da VM, será necessário ajustar apneas um arquivo.
