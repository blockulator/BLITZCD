; ; display library demo requires WB2.0 or greater ; ; see ABOUT requester for details ;  .main:   � setupui   � setuppalette   � doui   �  #msg=13:#flag=12:#exit=11:#about=10:#test=9:#mc=8:#bp=7:#fetch=6 #spres=5:#ham=4:#ehb=3:#dp=2:#smooth=1:#res=0:#w0=0  .doui:   bp=1:fetch=0:spres=0:res=0   ham=0:ehb=0:dp=0:smooth=0:mc=0   Ǝ #w0,#w0   ��     � displayflags     ev.l=�     � ev=$20 �� ev=$40 �! � readui   �� �=#exit   �  .readui:   � �     � #bp:bp=�@     � #res:res=�@     � #smooth:smooth=�@     � #dp:dp=�@     � #ehb:ehb=�@     � #ham:ham=�@     � #spres:spres=�@     � #fetch:fetch=�@     � #mc:mc=�@     � #test:� testmode     � #about:� doabout   � �   �  .displayflags:   flags.l=bp+smooth*$10+dp*$20+ehb*$40+ham*$80   flags.l+res*$100+spres*$400+fetch*$1000+mc*$10000   Ɣ #w0,#flag,"$"+܂(܄(flags),5)   �  .setupui:   � 0,0,0,640,256,2,-32768,"TEST",0,1   ƍ #w0,#msg,74,137,280,16,"MESSAGE:",1,""   ƍ #w0,#flag,74,119,280,16,"FLAGS:",1,""   Ƃ #w0,#exit,238,156,112,18,"EXIT",16   Ƃ #w0,#about,121,156,105,17,"ABOUT",16   Ƃ #w0,#test,8,156,97,16,"TEST",16   ƃ #w0,#mc,5,104,26,11,"24BIT PALETTE",2   f$="%2ld":Ə $8008002a,&f$,$80080029,2   Ƌ #w0,#bp,132,22,202,13,"BITPLANES =   ",1,1,8,1   Ƅ #w0,#fetch,100,4,233,15,"FETCH MODE",1,"  SINGLE  |DOUBLE (1)|DOUBLE (2)|  TRIPLE  "   Ƈ #w0,#spres,185,85,161,28,"",2," LO-RES SPRITES  | HI-RES SPRITES  |SUPER-RES SPRITES"   ƃ #w0,#ham,4,89,26,11,"HAM",2   ƃ #w0,#ehb,5,74,26,11,"EHB",2   ƃ #w0,#dp,5,59,26,11,"DUAL PLAYFIELD",2   ƃ #w0,#smooth,6,45,26,11,"SMOOTH SCROLL",2   Ƈ #w0,#res,186,45,113,28,"",2,"  LO-RES   |  HI-RES   |SUPER-HIRES"   � #w0,111,15,369,188,$1000,"DISPLAY DEMO",1,2   �  .doabout   � 1,111,15,369,188,$1008,"ABOUT DISPLAY DEMO",1,2   � 1   ݂ ""   ݂ "*        BLITZ 2 DISPLAY DEMO v0.1          *"   ݂ "         -------------------------           "   ݂ " Hopefully this program will enable you to  "   ݂ " find out what value to put in Blitz's new  "   ݂ " InitCopList command's flags field.         "   ݂ ""   ݂ " If you select smoothscroll you can move da "   ݂ " mouse around in the test mode. I chose not "   ݂ " to blit the shape all around the screen    "   ݂ " as this logic would have slowed it down    "   ݂ " Instead the program just blits the shape   "   ݂ " down in the top left where it picks it up. "   ݂ ""   ݂ " Sorry no dualplayfield testmode as yet....  "   ݂ " Also would like to support the different    "   ݂ " Blit modes such as BBlit and QBlit.         "   ݂ ""   ݂ "Maybe add sprites and overscan stuff?        "   ��:�� �=$200   �< 1   �  .setuppalette:   � i=1 � 255:� 0,i,�(255),�(255),�(255):�   �  .testmode:   ;   width=320*(2^res):height=256:cols=2^bp   � ehb �! cols=32   � ham �! � bp<7 �! cols=16 �" cols=64   ;   � cols>32 �B mc=0     Ɣ #w0,#msg,"SET 24BIT MODE FOR > 32 COLORS!"     � 0:�   ��   � fetch=0 �B ((res=1 �B bp>4) �� (res=2 �B bp>2))     Ɣ #w0,#msg,"INCREASE FETCH MODE!"     � 0:�   ��   � fetch<3 �B res=2 �B bp>4     Ɣ #w0,#msg,"INCREASE FETCH MODE!"     � 0:�   ��   ;   bwidth=width:bheight=height:� smooth �! bwidth*2:bheight*2   � 0,bwidth,bheight,bp   � i=0 � 100:�� �(bwidth),�(bheight),�(cols):�   �� 7,7,7,-1:� 0,0,0,16,16   ;   ǂ 0,44,256,flags,8,cols,0   ǆ 0,0   �4   � ��:� 0,0,bwidth/2,bheight/2   ;   �0 5     � smooth �! Ǆ 0,0,�,�     nf.l+1   � �0   ;   ǃ 0:Ǆ 0,0   ;   nf.l=0:nb.l=0   � �(0)=0:� 0,0,0:nb+1:��   �1 5   �7 20   �5   msg$="16x16 blits per frame = "+݃(nb/nf)   Ɣ #w0,#msg,msg$   � 