package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.*;

/**
 * VisitorInt interface
 * @author Chiara Criscuolo
 * It accept every message and visit it calling the right method in Observable
 */

public interface VisitorInt {

    void visitActionEvent(ActionEvent actionEvent);

    void visitActionEventWindow(ActionEventWindow actionEventWindow);

    void visitActionEventPlayer(ActionEventPlayer actionEventPlayer);

    void visitConnectMessage(ConnectMessage connectMessage);

    void visitDataMessage(DataMessage dataMessage);

    void visitWindowAnswerMessage(WindowAnswerMessage windowAnswerMessage);

    void visitWindowInitialMessage(WindowInitialMessage windowInitialMessage);

    Observable getObservable();

    void visitActionEventTimerEnd(ActionEventTimerEnd actionEventTimerEnd);

    void visitBeginTurnMessage(BeginTurnMessage beginTurnMessage);

    void visitPlayerConnectionMessage(Boolean connection);
}
