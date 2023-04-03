; ==============================================================================
; SHOOT MANAGEMENT (AGENT)
; SHIPPOSX                     (W)      (A0)
; SHIPPOSY-SHIPHEIG/2-SHOTRAD  (W)     2(A0)
; ==============================================================================

; ------------------------------------------------------------------------------
SHOTINIT
; INITIALIZE SHOOT.
; INPUT    : NONE
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVEM.L D0/A0,-(A7)
            MOVE.W  (SHIPPOSX),(A0)
            MOVE.W  (SHIPPOSY),2(A0)
            SUB.W   #SHIPHEIG/2,2(A0)
            SUB.W   #SHOTRAD,2(A0)
            MOVE.W  #SHOTSPED,(SHOTVELY)
            MOVEM.L (A7)+,D0/A0
            RTS
            
; ------------------------------------------------------------------------------
SHOTUPD
; UPDATES THE SHOOT.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            MOVEM.W D0-D3/A0-A1,-(A7)

            ; UPDATE SHOOT COORDINATES
            LEA.L   (A0),A1
            MOVE.W  2(A1),D1
            SUB.W   (SHOTVELY),D1
            

            ; CHECK VERTICAL COLLISIONS
            CMP.W   #SCRHEIGH-SHOTRAD,D1
            BGT     .COLBOT
            CMP.W   #0,D1
            BLT     .COLTOP
            MOVE.W  #2,D0
            JSR     DMMFRSTO
.LOOP       CMP.L   #0,A0
            BEQ     .CHKGUIDE
            ADD.L   #8,A0

            BRA     .MAREJY0
            
.MAREJY0    ; Y0    COLLISION
            MOVE.W  2(A1),D3
            MOVE.W  2(A0),D2
            ADD.W   #MARHEIGH/2+SHOTRAD,D2
            CMP.W   D2,D3
            BLT     .MAREJY1          
            BRA     .NEXT
            
.MAREJY1
            MOVE.W  2(A0),D2
            SUB.W   #MARHEIGH/2+SHOTRAD,D2
            CMP.W   D2,D3
            BGT     .MAREJX0
            BRA     .NEXT
            
.MAREJX0    
            MOVE.W  (A0),D2
            SUB.W   #MARWIDTH/2 ,D2
            SUB.W   #SHOTRAD,D2
            MOVE.W  (A1),D3
            CMP.W   D2,D3
            BGT     .MAREJX1
            BRA     .NEXT
            
.MAREJX1    MOVE.W  (A0),D2
            ADD.W   #MARWIDTH/2,D2
            ADD.W   #SHOTRAD,D2
            CMP.W   D2,D3
            BGT     .NEXT
            MOVE.W  #0,D7
            TRAP    #2
            ;DELETE MARCIANO
            JSR     AGLKILL
            SUBQ.W  #1,(MAXMAR)
            ;WIN POINTS BY THE DEATH OF THE MARCIANO
            ADD.W  #POINTS,(SCOOUT)
            LEA.L   (A1),A0
            ;ALSO THE SHOOT WILL BE DELETED
            JSR     AGLKILL
.NEXT       JSR     DMMNEXTO
            CMP.W   #2,D0
            BNE     .GUIDELOOP
            BRA     .LOOP
            
            ;NOW IT WILL CHECK THE COLLISION WITH MARGUIDE
.CHKGUIDE   MOVE.W  #4,D0
            JSR     DMMFRSTO
.GUIDELOOP  CMP.L   #0,A0
            BEQ     .DONE
            ADD.L   #8,A0

            BRA     .MAREJY0
            BRA     .DONE
            

            

            ; BOTTOM COLLISION
.COLBOT     NEG.W   (SHOTVELY)
            MOVE.W  #SCRHEIGH-SHOTRAD,D1
            BRA     .DONE
         
            
            
            ; TOP COLLISION
.COLTOP     JSR   AGLKILL
            BRA   .DONE

  

           ; STORE POSITION
.DONE      MOVE.W  D1,2(A1)
           MOVEM.W (A7)+,D0-D3/A0-A1

            RTS
            
; ------------------------------------------------------------------------------
SHOTPLOT
; PLOT THE SHOOT.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVEM.L D0-D4,-(A7)

            ; SET PEN COLOR
            MOVE.B  #80,D0
            MOVE.L  #SHOTPCOL,D1
            TRAP    #15
            
            ; SET FILL COLOR
            MOVE.B  #81,D0
            MOVE.L  #SHOTFCOL,D1
            TRAP    #15
            
            ; DEFINE COORDINATES
            MOVE.W  (A0),D1
            SUB.W   #SHOTRAD,D1
            MOVE.W  2(A0),D2
            SUB.W   #SHOTRAD,D2
            MOVE.W  D1,D3
            ADD.W   #2*SHOTRAD,D3
            MOVE.W  D2,D4
            ADD.W   #2*SHOTRAD,D4
            
            ; DRAW CIRCLE
            MOVE.B  #88,D0
            TRAP    #15
            
            MOVEM.L (A7)+,D0-D4

            RTS



*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
