package it.polimi.ingsw.LM26.observers.serverController;

import it.polimi.ingsw.LM26.systemNetwork.serverNet.dataProtocol.*;

public class VisitorMessage implements VisitorInt {

    Observable observable;

    public VisitorMessage(){
        observable = new Observable();
    }

    @Override
    public Observable getObservable() {
        return observable;
    }

    @Override
    public void visitActionEventTimerEnd(ActionEventTimerEnd actionEventTimerEnd) {
        observable.notify(actionEventTimerEnd);
    }

    @Override
    public void visitBeginGame(Boolean connection) {
        observable.notify(connection);
    }

    @Override
    public void visitBeginTurnMessage(BeginTurnMessage beginTurnMessage) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public void visitActionEvent(ActionEvent actionEvent) {
        observable.notify(actionEvent);
    }

    @Override
    public void visitActionEventWindow(ActionEventWindow actionEventWindow) {
        observable.notify(actionEventWindow);
    }

    @Override
    public void visitActionEventPlayer(ActionEventPlayer actionEventPlayer) {
        observable.notify(actionEventPlayer);
    }

    @Override
    public void visitConnectMessage(ConnectMessage connectMessage) {
        observable.notify(connectMessage);
    }

    @Override
    public void visitDataMessage(DataMessage dataMessage) {
        observable.notify(dataMessage);
    }

    @Override
    public void visitWindowAnswerMessage(WindowAnswerMessage windowAnswerMessage) {
        observable.notify(windowAnswerMessage);
    }

    @Override
    public void visitWindowInitialMessage(WindowInitialMessage windowInitialMessage) {
        observable.notify(windowInitialMessage);
    }

}
