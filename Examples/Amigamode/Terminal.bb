; ; tiny terminal program using serial port by Simon ;  � 0,0,"Projects" � 0,0,0,0,"Quit    ","Q"  � 0,12,"BlitzTerm"  winheight=�-12 columns=�(winheight/8)-2  � 0,0,12,640,winheight,$1900,"",0,2,1 � 0  � 0:� 0  � �("serial.device",0,2400,0)=0 �! �  ��   ev.l=�:� ev=256 �! �   � x*8,y*8:� x*8,y*8,x*8+8,y*8+7,3  ;cursor on   a$=�   � a$<>"" �! � 0,܇(a$)   b.w=�(0)   � b>0     � b       � x*8,y*8,x*8+8,y*8+7,0              ;cursor off       � 10       � 8         x-1       � 13         � y<columns           ݂ "":y+1:x=0         �"           � 0,0,640,winheight,0,8:x=0         ��       �:݁ ܆(b):x+1     � �   �� ��  