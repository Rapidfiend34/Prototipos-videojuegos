; ==============================================================================
; SHOOTMAR MANAGEMENT (AGENT)
; MARPOSX  (W)      (A1)
; MARPOSY  (W)     2(A1)
; ==============================================================================
            
; ------------------------------------------------------------------------------
SHTMAINT
; INITIALIZE SHOOT.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------
            MOVEM.L D0/A0-A1,-(A7)
            MOVE.W  (A4),(A0)
            MOVE.W  2(A4),2(A0)
            ADD.W   #MARHEIGH,2(A0)
            
            MOVE.W  #SHOTSPED,(SHOTVELY)
            MOVEM.L (A7)+,D0/A0-A1
            RTS
            
; ------------------------------------------------------------------------------
SHTMAUPD
; UPDATES THE SHOOT.
; INPUT    : NONE 
; OUTPUT   : NONE
; MODIFIES : NONE
; ------------------------------------------------------------------------------

            MOVEM.W D0-D3/A0-A1,-(A7)

            ; UPDATE SHOOT COORDINATES
            LEA.L   (A0),A1
            MOVE.W  2(A1),D1
            ADD.W   #SHOTSPED,D1
            
            ; CHECK SHIP'S NUCLEOUS COLLISION
            
              ; HORIZONTAL
            MOVE.W  (A1),D3
            ADD.W   #SHTMAWIT/2,D3
            MOVE.W  (SHIPPOSX),D2
            SUB.W   #SHIPWIDT/2,D2
            CMP.W   D2,D3
            BLT     .CHKMOT
            SUB.W   #SHTMAWIT,D3
            ADD.W   #SHIPWIDT,D2
            CMP.W   D2,D3
            BGT     .CHKMOT
            
              ; VERTICAL
            MOVE.W  2(A1),D3
            MOVE.W  (SHIPPOSY),D2
            ADD.W   #SHTMAHEI/2,D3
            ADD.W   #SHIPHEIG/2,D2
            CMP.W   D2,D3
            BGT     .CHKMOT
            SUB.W   #SHTMAHEI,D3
            SUB.W   #SHIPHEIG,D2
            CMP.W   D2,D3
            BLT     .CHKMOT
            BRA     .DESTROY
                       
            ; CHECK SHIP'S MOTORS COLLISION
            
              ; HORIZONTAL LEFT
.CHKMOT     MOVE.W  (SHIPPOSX),D2
            SUB.W   #SHIPWIDT+SHIPWIDT/2,D2
            MOVE.W  (A1),D3
            ADD.W   #SHTMAWIT/2,D3
            CMP.W   D2,D3
            BLT     .CHKMOTRI
            MOVE.W  (SHIPPOSX),D2
            SUB.W   #SHIPWIDT/2,D2
            CMP.W   D2,D3
            BGT     .CHKMOTRI
            
              ; VERTICAL
            MOVE.W  2(A1),D3
            ADD.W   #SHTMAHEI/2,D3
            MOVE.W  (SHIPPOSY),D2
            CMP.W   D2,D3
            BLT     .CHKMOTRI
            SUB.W   #SHTMAHEI,D3
            ADD.W   #SHIPHEIG,D2
            SUB.W   #SHIPHEIG/3,D2
            CMP.W   D2,D3
            BGT     .CHKMOTRI
            BRA     .DESTROY
            
              ; HORIZONTAL RIGHT
.CHKMOTRI   MOVE.W  (SHIPPOSX),D2
            ADD.W   #SHIPWIDT+SHIPWIDT/2,D2
            MOVE.W  (A1),D3
            SUB.W   #SHTMAWIT/2,D3
            CMP.W   D2,D3
            BGT     .CHKVECO
            MOVE.W  (SHIPPOSX),D2
            ADD.W   #SHIPWIDT/2,D2
            CMP.W   D2,D3
            BLT     .CHKVECO
              ; VERTICAL
            MOVE.W  2(A1),D3
            ADD.W   #SHTMAHEI/2,D3
            MOVE.W  (SHIPPOSY),D2
            CMP.W   D2,D3
            BLT     .CHKVECO
            SUB.W   #SHTMAHEI,D3
            ADD.W   #SHIPHEIG,D2
            SUB.W   #SHIPHEIG/3,D2
            CMP.W   D2,D3
            BGT     .CHKVECO
     
            
            ; KILLS THE SHOOTMAR AGENT AND UPDATES DE LIVE BAR
.DESTROY    MOVE.W  #1,D7
            TRAP    #2
            JSR     AGLKILL
            ADD.W   #SCRWIDTH/5,(LENGLIFE)   
            SUBQ.W  #1,(LIFES)
            SUBQ.W  #1,(MAXSHTMA)

            ; CHECK BORDER'S VERTICAL COLLISIONS
.CHKVECO    CMP.W   #SCRHEIGH-SHTMAHEI/2,D1
            BLT     .DONE
            JSR     AGLKILL
            SUBQ.W  #1,(MAXSHTMA)
            BRA     .DONE

         
          ; STORE POSITION
.DONE      MOVE.W  D1,2(A1)
          MOVEM.W (A7)+,D0-D3/A0-A1

            RTS
            
; ------------------------------------------------------------------------------
SHTMAPLT
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
            SUB.W   #SHTMAWIT/2,D1
            MOVE.W  2(A0),D2
            SUB.W   #SHTMAHEI,D2
            MOVE.W  D1,D3
            ADD.W   #SHTMAWIT,D3
            MOVE.W  D2,D4
            ADD.W   #SHTMAHEI,D4
            
            ; DRAW RECTANGLE
            MOVE.B  #87,D0
            TRAP    #15
            
            MOVEM.L (A7)+,D0-D4

            RTS


*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
