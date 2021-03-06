;
; how to get file names of a DOS dirctory
;
; good example of using system structure in Blitz2
; also now using lset$ to format text output
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

p$="w2:"                            ;directory name goes in p$
t$=Chr$(9)                          ;tab$

lock.l=Lock_(&p$,-2)                ;lock is dos system to access files

If Examine_(lock,myfib)<>0          ;if we get lock on p$
  While ExNext_(lock,myfib)<>0      ;skip through files
    USEPATH myfib
    If \_DirEntryType<0             ;if its not a directory
      Print LSet$(Peek$(&\_FileName),20)
      Print LSet$(Str$(\_Size),10)
      NPrint Date$(\ds_Days)
    Else
      fi$=Peek$(&\_FileName):NPrint "(dir)",fi$
    EndIf
  Wend
EndIf

UnLock_ lock

MouseWait
