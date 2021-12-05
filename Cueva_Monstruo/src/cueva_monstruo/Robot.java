/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cueva_monstruo;

/**
 *
 * @author pujol
 */
public class Robot extends Thread {

    private int x;
    private int y;

    static float velocidad = 500; // espera en milisegundos
    static int velocidad_max = 0;
    static int velocidad_min = 900;
    static int velocidad_salto = 100;
    private boolean moverse = false;

    private boolean tesoroEncontrado = false;
    private boolean monstruoEncontrado = false;
    private boolean[] percepciones = {false, false, false};
    private Direccion mov_previo = null;
    private boolean vivo;

    private Conocimiento[][] bc;

    public Robot(int y, int x, int elem) {
        this.vivo = true;
        this.x = x;
        this.y = y;
        bc = new Conocimiento[elem][elem];
        for (int i = 0; i < elem; i++) {
            for (int j = 0; j < elem; j++) {
                bc[i][j] = new Conocimiento();
            }
        }
        this.start();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public void run() {
        while (vivo) {
            System.out.println("espero");
            while (!tesoroEncontrado) {
                percibe();
                actualizaBC();
                try {
                    Thread.sleep((long) velocidad);
                } catch (InterruptedException ex) {
                    //System.out.println(ex.getMessage());
                }

                avanzar();
            }
            //volverAtras();
            muere();
        }
    }

    public void acelerar() {
        if (velocidad > velocidad_max) {
            velocidad -= velocidad_salto;
        }
    }

    public void decelerar() {
        if (velocidad < velocidad_min) {
            velocidad += velocidad_salto;
        }
    }

    public void muere() {
        this.vivo = false;
    }

    public void setMovimiento(boolean moverse) {
        this.moverse = moverse;
    }

    public boolean getMovimiento() {
        return this.moverse;
    }

    public float getVelocidad() {
        float vel = 0;
        if (moverse) {
            vel = 100 - ((velocidad / 1000) * 100);
        }
        return vel;
    }

    public void percibir(boolean[] percepciones) {
        this.percepciones = percepciones;

    }

    public void percibe() {
        Cueva_Monstruo.percepciones();
    }

    public void actualizaBC() {
        bc[y][x].setOk(true);
        if(percepciones[2]){
            tesoroEncontrado = true;
        }
        
        if (!percepciones[0] && !percepciones[1]) {
            if (y > 0) {
                bc[y - 1][x].setOk(true);
            }
            if (y < bc.length - 1) {
                bc[y + 1][x].setOk(true);
            }
            if (x > 0) {
                bc[y][x - 1].setOk(true);
            }
            if (x < bc.length - 1) {
                bc[y][x + 1].setOk(true);
            }
        }

        if (y > 0 && !bc[y - 1][x].isOk()) {
            if (bc[y - 1][x].isPosibleMonstruo() && !bc[y - 1][x].isPosiblePrecipicio() && !percepciones[0]) {
                bc[y - 1][x].setOk(true);
            } else if (!bc[y - 1][x].isPosibleMonstruo() && bc[y - 1][x].isPosiblePrecipicio() && !percepciones[1]) {
                bc[y - 1][x].setOk(true);
            }
        }
        if (y < bc.length - 1 && !bc[y + 1][x].isOk()) {
            if (bc[y + 1][x].isPosibleMonstruo() && !bc[y + 1][x].isPosiblePrecipicio() && !percepciones[0]) {
                bc[y + 1][x].setOk(true);
            } else if (!bc[y + 1][x].isPosibleMonstruo() && bc[y + 1][x].isPosiblePrecipicio() && !percepciones[1]) {
                bc[y + 1][x].setOk(true);
            }
        }
        if (x > 0 && !bc[y][x - 1].isOk()) {
            if (bc[y][x - 1].isPosibleMonstruo() && !bc[y][x - 1].isPosiblePrecipicio() && !percepciones[0]) {
                bc[y][x - 1].setOk(true);
            } else if (!bc[y][x - 1].isPosibleMonstruo() && bc[y][x - 1].isPosiblePrecipicio() && !percepciones[1]) {
                bc[y][x - 1].setOk(true);
            }
        }
        if (x < bc.length - 1 && !bc[y][x + 1].isOk()) {
            if (bc[y][x + 1].isPosibleMonstruo() && !bc[y][x + 1].isPosiblePrecipicio() && !percepciones[0]) {
                bc[y][x + 1].setOk(true);
            } else if (!bc[y][x + 1].isPosibleMonstruo() && bc[y][x + 1].isPosiblePrecipicio() && !percepciones[1]) {
                bc[y][x + 1].setOk(true);
            }
        }

        if (!monstruoEncontrado) {
            if (percepciones[0]) {
                System.out.println("huelo");
                bc[y][x].setHedor(true);
                if (y > 0 && !bc[y - 1][x].isOk() && bc[y - 1][x].isPosibleMonstruo()) {
                    monstruoEncontrado = true;
                    for (int i = 0; i < bc.length; i++) {
                        for (int j = 0; j < bc.length; j++) {
                            if (bc[i][j].isPosibleMonstruo() && i != y - 1 && j != x && !bc[i][j].isPosiblePrecipicio()) {
                                bc[i][j].setOk(true);
                            }
                        }
                    }
                }
                if (y < bc.length - 1 && !bc[y + 1][x].isOk() && bc[y + 1][x].isPosibleMonstruo()) {
                    monstruoEncontrado = true;
                    for (int i = 0; i < bc.length; i++) {
                        for (int j = 0; j < bc.length; j++) {
                            if (bc[i][j].isPosibleMonstruo() && i != y + 1 && j != x && !bc[i][j].isPosiblePrecipicio()) {
                                bc[i][j].setOk(true);
                            }
                        }
                    }
                }
                if (x > 0 && !bc[y][x - 1].isOk() && bc[y][x - 1].isPosibleMonstruo()) {
                    monstruoEncontrado = true;
                    for (int i = 0; i < bc.length; i++) {
                        for (int j = 0; j < bc.length; j++) {
                            if (bc[i][j].isPosibleMonstruo() && i != y && j != x - 1 && !bc[i][j].isPosiblePrecipicio()) {
                                bc[i][j].setOk(true);
                            }
                        }
                    }
                }
                if (x < bc.length - 1 && !bc[y][x + 1].isOk() && bc[y][x + 1].isPosibleMonstruo()) {
                    monstruoEncontrado = true;
                    for (int i = 0; i < bc.length; i++) {
                        for (int j = 0; j < bc.length; j++) {
                            if (bc[i][j].isPosibleMonstruo() && i != y && j != x + 1 && !bc[i][j].isPosiblePrecipicio()) {
                                bc[i][j].setOk(true);
                            }
                        }
                    }
                }
                if (!monstruoEncontrado) {
                    if (y > 0 && !bc[y - 1][x].isOk()) {
                        bc[y - 1][x].setPosibleMonstruo(true);
                    }
                    if (y < bc.length - 1 && !bc[y + 1][x].isOk()) {
                        bc[y + 1][x].setPosibleMonstruo(true);
                    }
                    if (x > 0 && !bc[y][x - 1].isOk()) {
                        bc[y][x - 1].setPosibleMonstruo(true);
                    }
                    if (x < bc.length - 1 && !bc[y][x + 1].isOk()) {
                        bc[y][x + 1].setPosibleMonstruo(true);
                    }
                }
            }
        } else {
            if (percepciones[0]) {
                if (y > 0 && !bc[y - 1][x].isOk() && !bc[y - 1][x].isPosibleMonstruo() && !bc[y - 1][x].isPosiblePrecipicio()) {
                    bc[y - 1][x].setOk(true);
                }
                if (y < bc.length - 1 && !bc[y + 1][x].isOk() && !bc[y + 1][x].isPosibleMonstruo() && !bc[y + 1][x].isPosiblePrecipicio()) {
                    bc[y + 1][x].setOk(true);
                }
                if (x > 0 && !bc[y][x - 1].isOk() && !bc[y][x - 1].isPosibleMonstruo() && !bc[y][x - 1].isPosiblePrecipicio()) {
                    bc[y][x - 1].setOk(true);
                }
                if (x < bc.length - 1 && !bc[y][x + 1].isOk() && !bc[y][x + 1].isPosibleMonstruo() && !bc[y][x + 1].isPosiblePrecipicio()) {
                    bc[y][x + 1].setOk(true);
                }
            }
        }

        if (percepciones[1]) {
            bc[y][x].setBrisa(true);
            if (y > 0 && !bc[y - 1][x].isOk()) {
                bc[y - 1][x].setPosiblePrecipicio(true);
            }
            if (y < bc.length - 1 && !bc[y + 1][x].isOk()) {
                bc[y + 1][x].setPosiblePrecipicio(true);
            }
            if (x > 0 && !bc[y][x - 1].isOk()) {
                bc[y][x - 1].setPosiblePrecipicio(true);
            }
            if (x < bc.length - 1 && !bc[y][x + 1].isOk()) {
                bc[y][x + 1].setPosiblePrecipicio(true);
            }
        }
        bc[y][x].setVisitada(true);
    }

    public void mover(Direccion dir) {
        switch (dir) {
            case NORTE:
                this.y -= 1;
                break;
            case SUR:
                this.y += 1;
                break;
            case ESTE:
                this.x += 1;
                break;
            case OESTE:
                this.x -= 1;
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        mov_previo = dir;
        Cueva_Monstruo.moverRobot(this.y, this.x, dir);
    }

    public void moverAtras(Direccion dir) {
        Direccion atras = null;
        switch (dir) {
            case NORTE:
                atras = Direccion.SUR;
                this.y += 1;
                break;
            case SUR:
                atras = Direccion.NORTE;
                this.y -= 1;
                break;
            case ESTE:
                atras = Direccion.OESTE;
                this.x -= 1;
                break;
            case OESTE:
                atras = Direccion.ESTE;
                this.x += 1;
                break;
            default:
                System.out.println("ERROR");
                break;
        }
        Cueva_Monstruo.moverRobot(this.y, this.x, atras);
        mov_previo = atras;
    }

    public void avanzar() {
        if (y > 0 && bc[y - 1][x].isOk() && !bc[y - 1][x].isVisitada()) {
            mover(Direccion.NORTE);
        } else if (x < bc.length - 1 && bc[y][x + 1].isOk() && !bc[y][x + 1].isVisitada()) {
            mover(Direccion.ESTE);
        } else if (y < bc.length - 1 && bc[y + 1][x].isOk() && !bc[y + 1][x].isVisitada()) {
            mover(Direccion.SUR);
        } else if (x > 0 && bc[y][x - 1].isOk() && !bc[y][x - 1].isVisitada()) {
            mover(Direccion.OESTE);
        } else if (y > 0 && bc[y - 1][x].isOk() && !Direccion.SUR.equals(mov_previo)) {
            mover(Direccion.NORTE);
        } else if (x < bc.length - 1 && bc[y][x + 1].isOk() && !Direccion.OESTE.equals(mov_previo)) {
            mover(Direccion.ESTE);
        } else if (y < bc.length - 1 && bc[y + 1][x].isOk() && !Direccion.NORTE.equals(mov_previo)) {
            mover(Direccion.SUR);
        } else if (x > 0 && bc[y][x - 1].isOk() && !Direccion.ESTE.equals(mov_previo)) {
            mover(Direccion.OESTE);
        } else if (y > 0 && bc[y - 1][x].isOk()) {
            mover(Direccion.NORTE);
        } else if (x < bc.length - 1 && bc[y][x + 1].isOk()) {
            mover(Direccion.ESTE);
        } else if (y < bc.length - 1 && bc[y + 1][x].isOk()) {
            mover(Direccion.SUR);
        } else if (x > 0 && bc[y][x - 1].isOk()) {
            mover(Direccion.OESTE);
        }
    }
}
