package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GameKeyHandler implements EventHandler<KeyEvent> {

    GamePanel gPanel;

    public boolean moveUp, moveDown, moveLeft, moveRight, enterPressed;
    public boolean showTextDebug = false;

    public GameKeyHandler(GamePanel gPanel) {
        this.gPanel = gPanel;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getEventType().toString()) {
            case "KEY_PRESSED":
                switch (event.getCode()) {
                    case Z:
                    case UP:
                        if (gPanel.gameState == gPanel.playState) {
                            moveUp = true;
                        } else if (gPanel.gameState == gPanel.characterState) {
                            if (gPanel.ui.slotRow != 0) {
                                gPanel.ui.slotRow--;
                            }
                        }
                        break;
                    case S:
                    case DOWN:
                        if (gPanel.gameState == gPanel.playState) {
                            moveDown = true;
                        } else if (gPanel.gameState == gPanel.characterState) {
                            if (gPanel.ui.slotRow != 3) {
                                gPanel.ui.slotRow++;
                            }
                        }
                        break;
                    case Q:
                    case LEFT:
                        if (gPanel.gameState == gPanel.playState) {
                            moveLeft = true;
                        } else if (gPanel.gameState == gPanel.characterState) {
                            if (gPanel.ui.slotCol != 0) {
                                gPanel.ui.slotCol--;
                            }
                        }
                        break;
                    case D:
                    case RIGHT:
                        if (gPanel.gameState == gPanel.playState) {
                            moveRight = true;
                        } else if (gPanel.gameState == gPanel.characterState) {
                            if (gPanel.ui.slotCol != 5) {
                                gPanel.ui.slotCol++;
                            }
                        }
                        break;
                    case C: 
                        if (gPanel.gameState == gPanel.playState) {
                            gPanel.gameState = gPanel.characterState;
                        } else if (gPanel.gameState == gPanel.characterState) {
                            gPanel.gameState = gPanel.playState;
                        }
                        break;
                    case P:
                        if (gPanel.gameState == gPanel.playState) {
                            gPanel.gameState = gPanel.pauseState;
                        } else if (gPanel.gameState == gPanel.pauseState) {
                            gPanel.gameState = gPanel.playState;
                        }
                        break;
                    case ENTER:
                        if (gPanel.gameState == gPanel.dialogueState) {
                            gPanel.gameState = gPanel.playState;
                        } else if (gPanel.gameState == gPanel.playState) {
                            enterPressed = true;
                        } else if (gPanel.gameState == gPanel.characterState) {
                            gPanel.player.selectItem();
                        }
                        break;
                    case T:
                        if (showTextDebug == false) {
                            showTextDebug = true;
                        }
                        else {
                            showTextDebug = false;
                        }
                        break;
                    default:
                        break;
                }
                break;

            case "KEY_RELEASED":
                switch (event.getCode()) {
                    case W:
                    case UP:
                        moveUp = false;
                        break;
                    case S:
                    case DOWN:
                        moveDown = false;
                        break;
                    case A:
                    case LEFT:
                        moveLeft = false;
                        break;
                    case D:
                    case RIGHT:
                        moveRight = false;
                        break;
                    default:
                        break;
                }
                break;

            default:
                break;
        }
    }

}
