select concat('"', 'TYPE=',c.codEvento,'&OUTLET=',c.codTomada,'&RFID=',e.rfid,'&OFFSET=',c.`offset`,'&GAIN=',c.gain,'&RMS='
,c.eficaz,'&MV=',c.valorMedio,'&MV2=',c.vm2,'&UNDER=',c.under,'&OVER=',c.over,'&DURATION=',c.duration
,'&SIN=',group_concat(h.sen separator ';'),'&COS=',group_concat(h.cos separator ';'), '&DATE=', date_format(c.dataatual, '%Y-%m-%d'),';', date_format(c.dataatual, '%H:%i:%s'), '"'
) from capturaatual c ,equipamento e, harmatual h
where c.codEquip = e.codEquip
and h.codCaptura = c.codCaptura
group by c.codCaptura;
