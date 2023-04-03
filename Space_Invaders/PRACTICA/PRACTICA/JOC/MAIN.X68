; ==============================================================================
; TITLE       : MARCIANOS 68K
; DESCRIPTION : New version of the classic Space Invaders
; AUTHOR      : Aarón Fernández Ramón y Tomás Dengra Femenia
; HISTORY     :
; ==============================================================================

            ORG     $1000
            
            INCLUDE "CONST.X68"
            INCLUDE "SYSCONST.X68"
            INCLUDE "LIB/DMMCONST.X68"
            INCLUDE "LIB/UTLCONST.X68"
            INCLUDE "LIB/DMMCODE.X68"
            INCLUDE "SYSTEM.X68"
            INCLUDE "LIB/AGLCODE.X68"
            INCLUDE "LIB/UTLCODE.X68"
            INCLUDE "SHIP.X68"
            INCLUDE "SHOOTMAR.X68"
            INCLUDE "SHOOT.X68"
            INCLUDE "MARGUIDE.X68"
            INCLUDE "MARCIANOS.X68"
            INCLUDE "SCORE.X68"
            INCLUDE "GAME.X68"
            INCLUDE "INTRO.X68"
            INCLUDE "INSTRUCTIONS.X68"
            INCLUDE "WIN.X68"
            INCLUDE "GOVER.X68"
            INCLUDE "MOUSE.X68"
            INCLUDE "BUTTON.X68"
            INCLUDE "STATES.X68"
           
START

; --- INITIALIZATION -----------------------------------------------------------

            ORI.W   #$0700,SR
            JSR     SYSINIT
            JSR     UTLINIT
            JSR     MOINIT
            JSR     STAINIT
            

.LOOP

; --- UPDATE -------------------------------------------------------------------

; READ INPUT DEVICES
            TRAP    #KBDUPTN
            JSR     MOINTE
            JSR     MOMOVE
            


; UPDATE GAME LOGIC

            JSR     STAUPD

; --- SYNC ---------------------------------------------------------------------

.WINT       TST.B   (SCRINTCT)
            BEQ     .WINT
            CLR.B   (SCRINTCT)

; --- PLOT ---------------------------------------------------------------------
            
            JSR     STAPLOT
            TRAP    #SCRUPTN                ; UPDATE DOUBLE BUFFER

            BRA     .LOOP
            
            SIMHALT
            
            INCLUDE "VARS.X68"
            INCLUDE "LIB/UTLVARS.X68"
            INCLUDE "SYSVARS.X68"
            INCLUDE "LIB/DMMVARS.X68"

            END     START


*~Font name~Fixedsys~
*~Font size~24~
*~Tab type~0~
*~Tab size~4~
