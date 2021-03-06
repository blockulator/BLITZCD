;
; recursive DOS directory program
;
; note how we use global myfib as it is just a memory block
; that dos fills out when we do the ExNext_ unlike the lock
; which has to be LOCAL because of the recursive nature of
; the program
;

NEWTYPE.FileInfoBlock
   _DiskKey.l
   _DirEntryType.l
   _FileName.b[108]
   _Protection.l
   _EntryType.l
   _Size.l
   _NumBlocks.l
   ds_Days.l
   ds_Minute.l
   ds_Tick.l
   _Comment.b[80]
   _Reserved.b[36]
End NEWTYPE

DEFTYPE .FileInfoBlock myfib        ;myfib holds infoblock

Statement listdir{dir$}
  SHARED myfib,tab:USEPATH myfib
  lock.l=Lock_(&dir$,-2)                   ;lock is dos system to access files
  If Examine_(lock,myfib)<>0               ;if we get lock on p$
    NPrint "DIRECTORY - ",dir$:tab+2
    While ExNext_(lock,myfib)<>0           ;skip through files
      If \_DirEntryType<0                  ;if its not a directory
        Print String$(" ",tab)
        Print LSet$(Peek$(&\_FileName),16)
        Print LSet$(Str$(\_Size),10)
        NPrint Date$(\ds_Days)
      Else
        fi$=Peek$(&\_FileName)
        listdir{dir$+fi$}
      EndIf
    Wend
    tab-2
  EndIf
  UnLock_ lock
End Statement

listdir {"w2:"}

MouseWait
