/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.GamePanelConstants;
import view.GamePanel;

/**
 *
 * Program created by
 * @author Anthony Alvarez Delgado
 * Software Engeneer Student - UIA
 *
 */
public class KeyHandler implements KeyListener, GamePanelConstants{

    
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    
    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    public void keyTyped(KeyEvent e) {
    }

    //Nos controla todo lo que pasa cuando se presiona una tecla
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(gamePanel.gameState == gamePanel.PLAY_STATE){
            playState(code);
        }
        
        else if(gamePanel.gameState == gamePanel.DIALOGUE_STATE){
            dialogueState(code);
            
        }
        else if(gamePanel.gameState == gamePanel.PAUSE_STATE){
            pauseState(code);
        }
        else if(gamePanel.gameState == gamePanel.TITLE_STATE){
            titleState(code);
            
        }
        else if (gamePanel.gameState == gamePanel.CHARACTER_STATE){ 
            characterState(code);
        }
        else if (gamePanel.gameState == gamePanel.OPTIONS_STATE){ 
            optionsState(code);
        }
        else if (gamePanel.gameState == gamePanel.OVER_STATE){ 
            gameOverState(code);
        }
        
        else if (gamePanel.gameState == gamePanel.WIN_STATE){ 
            gameOverState(code);
        }
        
    }
    //Nos controla todo lo que pasa cuando se presiona una tecla en el play state
    public void playState (int code){

            if(code==KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if(code==KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if(code==KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if(code==KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
            if(code==KeyEvent.VK_P){
                gamePanel.gameState = gamePanel.PAUSE_STATE; 
            }        
            if(code==KeyEvent.VK_C){
                gamePanel.gameState = gamePanel.CHARACTER_STATE; 
            }        
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            if(code == KeyEvent.VK_F){
                shotKeyPressed = true;
            }
            if(code == KeyEvent.VK_ESCAPE){
                gamePanel.gameState = gamePanel.OPTIONS_STATE;
            }
    }
    //Nos controla todo lo que pasa cuando se presiona una tecla en el pause state
    public void pauseState (int code){
        if(code==KeyEvent.VK_P)
            gamePanel.gameState = gamePanel.PLAY_STATE;
    }
    //Nos controla todo lo que pasa cuando se presiona una tecla en el dialogue state
    public void dialogueState (int code){
        if (code == KeyEvent.VK_ENTER)
            gamePanel.gameState = gamePanel.PLAY_STATE;
    }
    //Nos controla todo lo que pasa cuando se presiona una tecla en el character state
    public void characterState (int code){
        if(code==KeyEvent.VK_C)
            gamePanel.gameState = gamePanel.PLAY_STATE;
        if(code==KeyEvent.VK_W){
            if(gamePanel.ui.slotRow!=0){
                gamePanel.ui.slotRow--;
                gamePanel.playSFX(12);
            }
            
        }
        if(code==KeyEvent.VK_S){
            if(gamePanel.ui.slotRow != 3){
                gamePanel.ui.slotRow++;
                gamePanel.playSFX(12);
            }
            
        }
        if(code==KeyEvent.VK_A){
            if(gamePanel.ui.slotCol !=0){
                gamePanel.ui.slotCol--;
                gamePanel.playSFX(12);
            }
            
        }
        if(code==KeyEvent.VK_D){
            if(gamePanel.ui.slotCol != 4){
                gamePanel.ui.slotCol++;
                gamePanel.playSFX(12);
            }
            
        }
        if (code == KeyEvent.VK_ENTER){
            gamePanel.player.selectItem();
        }
        
        
        
    }
    //Nos controla todo lo que pasa cuando se presiona una tecla en el title state
    public void titleState(int code){
        if(code==KeyEvent.VK_ENTER){
            gamePanel.gameState = gamePanel.PLAY_STATE;
            gamePanel.stopMusic();
            gamePanel.playMusic(1);
        }
    }
    //Nos controla todo lo que pasa cuando se presiona una tecla en el options state
    private void optionsState(int code) {        
        if(code == KeyEvent.VK_ESCAPE){
            gamePanel.gameState = gamePanel.PLAY_STATE;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch(gamePanel.ui.subState){
            case 0:
              maxCommandNum = 4;
              break;
            case 2:
              maxCommandNum = 1;
              break;
        }
        
        if(code == KeyEvent.VK_W){
            gamePanel.ui.commandNum--;
            gamePanel.playSFX(12);
            if(gamePanel.ui.commandNum < 0){
                gamePanel.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gamePanel.ui.commandNum ++;
            gamePanel.playSFX(12);
            if(gamePanel.ui.commandNum > maxCommandNum){
                gamePanel.ui.commandNum = 0;
            }
        }
        
        if(code == KeyEvent.VK_A){
            if(gamePanel.ui.subState == 0){
                if(gamePanel.ui.commandNum == 0 && gamePanel.soundMusic.volumeScale > 0){
                    gamePanel.soundMusic.volumeScale--;
                    gamePanel.soundMusic.checkVolume();
                    gamePanel.playSFX(12);
                }
                if(gamePanel.ui.commandNum == 1 && gamePanel.soundSFX.volumeScale > 0){
                    gamePanel.soundSFX.volumeScale--;
                    gamePanel.playSFX(12);
                }
            }
        }
        
        if(code == KeyEvent.VK_D){
            if(gamePanel.ui.subState == 0){
                if(gamePanel.ui.commandNum == 0 && gamePanel.soundMusic.volumeScale < 5){
                    gamePanel.soundMusic.volumeScale++;
                    gamePanel.soundMusic.checkVolume();
                    gamePanel.playSFX(12);
                }
                if(gamePanel.ui.commandNum == 1 && gamePanel.soundSFX.volumeScale < 5){
                    gamePanel.soundSFX.volumeScale++;
                    gamePanel.playSFX(12);
                }
            }
        }
    }
    //Nos controla todo lo que pasa cuando se presiona una tecla en el game over state
    private void gameOverState(int code) {
        if(code == KeyEvent.VK_W){
            gamePanel.ui.commandNum--;
            if(gamePanel.ui.commandNum <0){
                gamePanel.ui.commandNum = 1;
            }
            gamePanel.playSFX(12);
        }
        if(code == KeyEvent.VK_S){
            gamePanel.ui.commandNum++;
            if(gamePanel.ui.commandNum >1){
                gamePanel.ui.commandNum = 0;
            }
            gamePanel.playSFX(12);
        }
        
        if(code == KeyEvent.VK_ENTER){
            if(gamePanel.ui.commandNum == 0){
                gamePanel.gameState = gamePanel.PLAY_STATE;
                gamePanel.restart();
            }else if(gamePanel.ui.commandNum == 1){
                gamePanel.gameState = gamePanel.TITLE_STATE;
                gamePanel.restart();
            }
            
        }
    }
    
    //Nos controla lo que pasa cuando se suelta la tecla
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code==KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code==KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code==KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
    }



}
