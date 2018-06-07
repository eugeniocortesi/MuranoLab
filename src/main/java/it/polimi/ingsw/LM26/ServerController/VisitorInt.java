package it.polimi.ingsw.LM26.ServerController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.ConnectMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.DataMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowAnswerMessage;
import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.WindowInitialMessage;

public interface VisitorInt {

    void visitActionEvent(ActionEvent actionEvent);
    void visitActionEventWindow(ActionEventWindow actionEventWindow);
    void visitActionEventPlayer(ActionEventPlayer actionEventPlayer);
    void visitConnectMessage(ConnectMessage connectMessage);
    void visitDataMessage(DataMessage dataMessage);
    void visitWindowAnswerMessage(WindowAnswerMessage windowAnswerMessage);
    void visitWindowInitialMessage(WindowInitialMessage windowInitialMessage);
    public Observable getObservable();

}
