; Tracker Tester - The BEST way to check the CIATrackerLibrary! ; ;                   Version 1.00 ; ;         Written by Crossbones/Binary Assault ;                    06/11/1995 ; ; Requires the following libraries to be used: ; -NeilsReqToolsLib.obj ; -RIAmosFuncLib.obj ; -CIATrackerLib.obj (Duuh!) ; ��:��  �	 DoRequest{text$}  success=�("Tracker Tester",text$,"Yes|No") �	 � success � �	  �	 Decode{name$}   worked=�   � �(0,name$)     � 0     size.l=�
(0)     � ��(0,size.l+5,%10)=0 �! �     � 0,0     � 0,��(0),size.l     �
     worked=�   ��   � 0 �	 � worked � �	   � 0 ��  � 2,1 � ��  � 0,"topaz.font",8 � 0,50,50,400,200,$2|$4|$8|$1000,"CIA Tracker Library Test - Version 1.0",0,1,1 �
 0  req$="First thing we need to do is find a"+܆(10)+"module to use. It must NOT be packed"+܆(10)+"in any form." success=�("Tracker Tester",req$,"OK!")  filename$=�("Module to use?","")  � �(0,filename$)   � 0 �"   success=�("Tracker Test","Gosh! Was it that hard?","Weeell?!")   �< 0   � ��  � w=0 � 8    �    � 1,0:� 1,1    ݂ "Currently Testing Slot#",݃(w)    ݂ ""    � w    ݁"Loading Module : ":� 2,0    � �(w,filename$)=�      ݂ "Failed!":� Error    ��    ݂"Passed."    � 1,0    ݁"Valid Module   : ":� 2,0    � �(w)=�      ݂"Passed."    �"      ݂"Failed!"    ��    � 1,0    ݁"Playing Module : ":� 2,0    success=�(w)    a=DoRequest{"Is the module playing?"}    � a=1      ݂"Passed."    �"      ݂"Failed!"      �� �:� Error    ��    � 1,0    �    ݁"Decoding Module: ":� 2,0    � w    � (Decode{filename$})=�      ݂"Failed!"      �� �:� Error    �"      ݂"Passed."      � w,��(0)    ��    � 1,0    ݁"Valid Module   : ":� 2,0    � �(w)=�      ݂"Passed."    �"      ݂"Failed!"    ��    � 1,0    � 1,0    ݁"Playing Module : ":� 2,0    success=�(w)    a=DoRequest{"Is the module playing?"}    � a=1      ݂"Passed."    �"      ݂"Failed!"      �� �:� Error    ��    � 1,0    ݁"Number/patterns: ":� 2,0      ݂ �(w)    � 1,0    ݁"Jump Pattern   : ":� 2,0    trks=�(w):pos=�(�(1)*trks)+1    � pos    a=DoRequest{"Did it just jump to another position?"}    � a=1      ݂"Passed."    �"      ݂"Failed!"      �� �:� Error    ��    � 1,0    ݁"Module Name    : ":� 2,0    name$=�(w)    � name$=""      ݂"Failed! (Possible.)"    �"      ݁ "Passed. (":� 1,0:݁ name$:� 2,0:݂")"    ��    � 1,0    ݁"Pause Module   : ":� 2,0    �    a=DoRequest{"Did the module pause?"}    �    b=DoRequest{"Is the module playing again?"}    � a=1 �B b=1      ݂ "Passed."    �"      ݂ "Failed!"      �� �:� Error    ��    � 1,0    ݁"Volume Control : ":� 2,0    � v=64 � 0 � -1:�7:� v:� v    a=DoRequest{"Did the volume go down?"}    � v=0 � 64:�7:� v:� v    b=DoRequest{"Did the volume go up?"}    � a=1 �B b=1      ݂ "Passed."    �"      ݂ "Failed!"      �� �:� Error    ��    � 1,0    ݁"Mask Control   : ":� 2,0    � %1001    a=DoRequest{"Is the tracker only playing on one side?"}    � %0110    b=DoRequest{"Is it now playing only on the other side?"}    � %1111    c=DoRequest{"Is it now playing on both sides?"}    � a=1 �B b=1 �B c=1      ݂ "Passed."    �"      ݂ "Failed!"      �� �:� Error    ��    � 1,0    � w:� 0    � w<>8      ݂""      ݂"** Press left mouse button to go on **"    �"      ݂""      ݂"      ** Test Finished! **"      ݂""      ݂"-Press left mouse button to exit-"    ��    �� � w .Fin �� �< 0 �  .Error success=�("Tracker Tester","Failure during the test","Shit!") � Fin 