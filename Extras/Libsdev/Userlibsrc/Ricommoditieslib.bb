;#ricommoditieslib=89  #ExecBase=4  #CXCMD_DISABLE    = 15  ; please disable yourself #CXCMD_ENABLE     = 17  ; please enable yourself #CXCMD_APPEAR     = 19  ; open your window, if you can #CXCMD_DISAPPEAR  = 21  ; go dormant #CXCMD_KILL       = 23  ; go away for good #CXCMD_LIST_CHG   = 27  ; Someone changed the broker list #CXCMD_UNIQUE     = 25  ; someone tried to create a broker                         ; with your name. Suggest you appear. !libheader {#ricommoditieslib,init,0,finit,0}  !acommand {#long} !args {#string,#string,#string} !libs !subs {_makecommodity+1,0,0} !name {"MakeCommodity","(name$,title$,description$"}  !acommand {#long} !args {#long,#string} !libs !subs {_addhotkey+1,0,0} !name {"SetHotKey","(hotkeynum,hotkey$)"}  !afunction {#long} !args !libs !subs {_hotkeyhit+1,0,0} !name {"HotKeyHit",""}  !acommand {#long} !args !libs !subs {_waitevent+1,0,0} !name {"WaitCommodity",""}  !afunction {#long} !args !libs !subs {_event+1,0,0} !name {"CommodityEvent",""}  !afunction {#long} !args !libs !subs {_exchangemessage+1,0,0} !name {"ExchangeMessage",""}  !afunction {#long} !args !libs !subs {_disabled+1,0,0} !name {"ExchangeDisable",""}  !afunction {#long} !args !libs !subs {_enable+1,0,0} !name {"ExchangeEnable",""}  !afunction {#long} !args !libs !subs {_appear+1,0,0} !name {"ExchangeAppear",""}  !afunction {#long} !args !libs !subs {_disappear+1,0,0} !name {"ExchangeDisAppear",""}  !afunction {#long} !args !libs !subs {_kill+1,0,0} !name {"ExchangeKill",""}  !afunction {#long} !args !libs !subs {_list+1,0,0} !name {"ExchangeListChange",""}  !afunction {#long} !args !libs !subs {_unqiue+1,0,0} !name {"ExchangeUnique",""}  !afunction {#long} !args !libs !subs {_cxdisable+1,0,0} !name {"CxDisable",""}  !afunction {#long} !args !libs !subs {_cxenable+1,0,0} !name {"CxEnable",""}  !afunction {#long} !args !libs !subs {_cxappear+1,0,0} !name {"CxAppear",""}  !afunction {#long} !args !libs !subs {_cxdisappear+1,0,0} !name {"CxDisAppear",""}  !afunction {#long} !args !libs !subs {_cxkill+1,0,0} !name {"CxKill",""}  !afunction {#long} !args !libs !subs {_cxlist+1,0,0} !name {"CxListChange",""}  !afunction {#long} !args !libs !subs {_cxunique+1,0,0} !name {"CxUnique",""}  !astatement !args {#long} !libs !subs {_status+1,0,0} !name {"SetStatus","on|off"}  finit:!nullsub{_libfinit,0,0} init:!nullsub{_libinit,0,0}  !libfin  ._libinit:   �~.l  4,a6   �z     cname(pc),a1   �~.l  #37,d0   �y     -552(a6)   �~.l  d0,cbase   ��   ._libfinit:   ��.l   cbase   �H     'oops   �~.l  4,a6   �~.l  cbase,a1   �y     -414(a6)   ��.l   msgport   �H     'oops   �~.l  cbase,a6   �~.l  cobject,a0   �y     -48(a6)   �~.l  #0,cobject   �~.l  msgport,a0   �Y     delmsgport   �~.l  #0,msgport   �� 'oops:   ��  ._waitevent:   ��.l   cobject   �H     'oops   �~.l  4,a6   �~.l  msgport,a0   �y     -372(a6)   �P     'ok   �~.l  d0,msg   ��.l #-1,d0   �� 'ok:   �~.l  msgport,a0   �y     -384(a6)   �~.l  d0,msg   ��.l #-1,d0   �� 'oops:   ��.l #0,d0   ��  ._event:   ��.l   cobject   �H     'oops   �~.l  4,a6   �~.l  msgport,a0   �y     -372(a6)   �~.l  d0,msg   ��.l   d0   �H     'oops   ��.l #-1,d0   �� 'oops:   ��.l #0,d0   ��   ._hotkeyhit:   ��.l   cobject   �H     'oops   ��.l   msg   �H     'oops   �Y     _getmessage   �\.l   #$20,cxmsgtype   �P     'oops ;  BSR     _replymessage   �~.l  cxmsgid,d0   �� 'oops:   �~.l  #-1,d0   ��  ._exchangemessage:   ��.l   cobject   �H     'oops   ��.l   msg   �H     'oops   �Y     _getmessage   �\.l   #$40,cxmsgtype   �P     'oops   �~.l  cxmsgid,d0   �� 'oops:   ��.l #0,d0   ��  oops:   ��.l #0,d0   ��  ._cxdisable:   �~.l  #CXCMD_DISABLE,d0   ��  ._cxenable:   �~.l  #CXCMD_ENABLE,d0   ��  ._cxappear:   �~.l  #CXCMD_APPEAR,d0   ��  ._cxdisappear:   �~.l  #CXCMD_DISAPPEAR,d0   ��  ._cxkill:   �~.l  #CXCMD_KILL,d0   ��  ._cxlist:   �~.l  #CXCMD_LIST_CHG,d0   ��  ._cxunique:   �~.l  #CXCMD_UNIQUE,d0   ��  ._disabled:   ��.l   cxmsgid   �H     oops   �\.l   #CXCMD_DISABLE,cxmsgid   �P     oops   ��.l #-1,d0   ��  ._enable:   ��.l   cxmsgid   �H     oops   �\.l   #CXCMD_ENABLE,cxmsgid   �P     oops   ��.l #-1,d0   ��  ._appear:   ��.l   cxmsgid   �H     oops   �\.l   #CXCMD_APPEAR,cxmsgid   �P     oops   ��.l #-1,d0   ��  ._disappear:   ��.l   cxmsgid   �H     oops   �\.l   #CXCMD_DISAPPEAR,cxmsgid   �P     oops   ��.l #-1,d0   ��  ._kill:   ��.l   cxmsgid   �H     oops   �\.l   #CXCMD_KILL,cxmsgid   �P     oops   ��.l #-1,d0   ��  ._list:   ��.l   cxmsgid   �H     oops   �\.l   #CXCMD_LIST_CHG,cxmsgid   �P     oops   ��.l #-1,d0   ��  ._unqiue:   ��.l   cxmsgid   �H     oops   �\.l   #CXCMD_UNIQUE,cxmsgid   �P     oops   ��.l #-1,d0   ��  ._getmessage:   �~.l  cbase,a6   �~.l  msg,a0   �y     -150(a6)   �~.l  d0,cxmsgid   �~.l  msg,a0   �y     -138(a6)   �~.l  d0,cxmsgtype   �~.l  msg,a0   �y     -144(a6)   �~.l  d0,cxmsgevent   ��  ._replymessage:   ��   ��.l d0-d7/a0-a6,-(a7)   �~.l  4,a6   �~.l  msg,a1   �y     -378(a6)   ��.l (a7)+,d0-d7/a0-a6   ��  ._addhotkey:   ��.l   cobject   �H     'oops   �~.l  cbase,a6   �~.l  d0,hotkey   �~.l  d1,hotkeystring    �z     cxfilter(pc),a0   �~.l  hotkey,d1   �D.l   #2,d1   ��.l   0(a0,d1.l)   �H     'ok   �~.l  0(a0,d1.l),a1   �~.l  #0,0(a0,d1.l)   �~.l  a1,a0   �y     -102(a6)    �z     cxsends(pc),a0   �~.l  hotkey,d1   �D.l   #2,d1   ��.l   0(a0,d1.l)   �H     'ok   �~.l  0(a0,d1.l),a1   �~.l  #0,0(a0,d1.l)   �~.l  a1,a0   �y     -102(a6)  'ok:   �~.l  hotkeystring,a0   �~.l  #0,a1   ��.l #1,d0             ; Create the FILTER object   �y     -30(a6)           ; CreateCxObj   ��.l   d0   �H     'oops    �~.l  d0,currentfilter   �z     cxfilter(pc),a0   �~.l  hotkey,d1   �D.l   #2,d1   �~.l  d0,0(a0,d1.l)    �~.l  cobject,a0   �~.l  currentfilter,a1   �y     -84(a6)           ; AttachCxObj   ��.l   d0   �H     'oops    �~.l  hotkey,a1        ; Create the Send object   �~.l  msgport,a0   �~.l  #3,d0   �y     -30(a6)   ��.l   d0   �H     'oops    �z     cxsends(pc),a0   �~.l  hotkey,d1   �D.l   #2,d1   �~.l  d0,0(a0,d1.l)   �~.l  currentfilter,a0   �~.l  d0,a1   �y     -84(a6)           ; AttachCxObj   ��.l   d0   �H     'oops    �~.l  cobject,a0   ��.l #1,d0   �y     -42(a6)           ; ActivateCxObj   ��.l #-1,d0   ��  'oops:   ��.l #0,d0   ��  'oops2:   �~.l  cobject,a0   �y     -66(a6)   ��   ._status:   ��.l   msgport   �H     'oops   �~.l  cbase,a6   �~.l  cobject,a0   �\.l    #-1,d0   �P     'ok   ��.l #1,d0   �X     'ok2 'ok:   ��.l   d0   �P     'oops 'ok2:   �y     -42(a6)           ; ActivateCxObj   ��.l #-1,d0   �� 'oops:   ��.l #0,d0   ��  .make: _makecommodity:   ��.l   msgport   �P     'oops   ��.l d0-d2,-(a7)   �Y     makemsgport   ��.l (a7)+,d1-d3   ��.l   d0   �H     'oops   �~.l  d0,msgport   �~.l  d1,nb_name   �~.l  d2,nb_title   �~.l  d3,nb_descr   �~.w  #3,nb_unique   �~.b  #5,nb_version   �~.w  #4,nb_flags   �~.b  #0,nb_pri   �~.l  msgport,nb_port   �~.l  cbase,a6   �z     newbroker(pc),a0   ��.l #0,d0   �y     -36(a6)   ��.l   d0   �H     'oops   �~.l  d0,cobject   �~.l  cobject,a0   ��.l #1,d0   �y     -42(a6)           ; ActivateCxObj   ��.l #-1,d0   �� 'oops:   ��.l #0,d0   ��  .delmsgport:     �\.l   #0,a0     �H     'oops     �~.l  4,a6     �y     -672(a6) 'oops:     ��  .makemsgport:     �~.l  4,a6     �y     -666(a6)     ��  cbase:      �%.l  0 cname:      �%.b  "commodities.library",0    �' .newbroker:   nb_version: �%.b  0   'pad:       �%.b  0   nb_name:    �%.l  0   nb_title:   �%.l  0   nb_descr:   �%.l  0   nb_unique:  �%.w  0   nb_flags:   �%.w  0   nb_pri:     �%.b  0   'pad:       �%.b  0   nb_port:    �%.l  0   nb_reservedchannel: �%.w  0  .inputevent:   ie_nextevent: �%.l  0   ie_class:     �%.b  0   ie_subclass:  �%.b  0   ie_position:  �%.l  0   ie_timestamp: �%.l  0,0  msgport:        �%.l  0 cobject:        �%.l  0 cxfilter:       �%.l  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 cxsends:        �%.l  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 hotkey:         �%.l  0 cxmsgid:        �%.l  0 cxmsgtype:      �%.l  0 cxmsgevent:     �%.l  0 msg:            �%.l  0 hotkeystring:   �%.l  0 currentfilter:  �%.l  0 