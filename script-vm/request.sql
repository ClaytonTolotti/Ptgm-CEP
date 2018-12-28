select 
	concat(
		"TYPE="
		,c.codEvento
		,"&OUTLET="
		,c.codTomada
		,"&RFID="
		,e.rfid
		,"&OFFSET="
		,c.`offset`
		,"&GAIN="
		,c.gain
		,"&RMS="
		,c.eficaz
		,"&MV="
		,c.valorMedio
		,"&MV2="
		,c.vm2
		,"&UNDER="
		,c.under
		,"&OVER="
		,c.over
		,"&DURATION="
		,c.duration
		,"&SIN="
		,group_concat(h.sen separator ';')
		,"&COS="
		,group_concat(h.cos separator ';')
		) from capturaatual c ,equipamento e, harmatual h
where c.codEquip = e.codEquip
and h.codCaptura = c.codCaptura
group by c.codCaptura;

/*
TYPE=01&OUTLET=01&RFID=FFFF0002&OFFSET=2093&GAIN=444E6B09&RMS=3FA277C5&MV=00000000&MV2=00000000&UNDER=0000&OVER=0000&DURATION=0000&SIN=44A74464%3BC232764E%3BC02F3D9A%3BC12780FC%3B42618EF4%3BC1CC261E%3BC21668A5%3BC0E1178A%3BC164BAB6%3BC0A07318%3BC10AEC48%3BC095ACE4&COS=C41D1918%3B418A1530%3B41B06ECC%3B40F0FE2A%3B42321A4A%3BC0C2D6BE%3BBE3880E4%3BC01D72A3%3BC12024DE%3B3FF28A7C%3BC05C0DE6%3B4014D9FD"
*/
