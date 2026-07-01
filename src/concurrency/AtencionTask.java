package concurrency;

import models.SistemaTurnosModel;

public class AtencionTask implements Runnable {

    private SistemaTurnosModel model;

    public AtencionTask(SistemaTurnosModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        if (model != null) {
            model.atenderSiguienteAsync();
        }
    }
}
