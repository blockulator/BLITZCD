; ; andre's requesterlib  (rev bum6) ;  !libheader{#romulusrequesterlib,0,0,0,0}  !acommand{#long} ; !args {#string,#string,#string} !libs {#intuitionlib,#la6} !subs {_request1+1,0,0} ; !args {#word,#string,#string,#string} !libs {#windowslib,#ia1|#pd0,#intuitionlib,#la6} !subs {_request2+1,0,0} ; !name {"EasyRequest","Result=EasyRequest([#Window,]title$,body$,gtext$)"}  !libfin  _request2:   �~.l  (a1),a0   �~.l  d1,es_Title   �~.l  d2,es_TextFormat   �~.l  d3,es_GadgetFormat   �X _request  _request1:   ��.l a0,a0   �~.l  d0,es_Title   �~.l  d1,es_TextFormat   �~.l  d2,es_GadgetFormat  _request:   ��.l a3-a5,-(a7)    �~.l  es_Title,d0   �~.l  -(a2),d1   �8 $cf01   �~.l  d0,es_Title    �~.l  es_TextFormat,d0   �~.l  -(a2),d1   �8 $cf01   �~.l  d0,es_TextFormat    �~.l  es_GadgetFormat,d0   �~.l  -(a2),d1   �8 $cf01   �~.l  d0,es_GadgetFormat    �z.l EasyStruct(pc),a1   ��.l a2,a2   ��.l a3,a3   �y -588(a6)   ��.l (a7)+,a3-a5   ��  EasyStruct: es_StructSize:  �%.l  �/.EasyStruct es_Flags: �%.l  0 es_Title: �%.l  0 es_TextFormat:  �%.l  0 es_GadgetFormat:  �%.l 0   