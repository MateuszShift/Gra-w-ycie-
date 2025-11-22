abstract class KolizjaHandler {
    protected KolizjaHandler next;

    public void setNext(KolizjaHandler next) {
        this.next = next;
    }

    public void handle(Organizm pierwszy, Organizm drugi, Swiat swiat, Napis napis) {
        if (!this.tryHandle(pierwszy, drugi, swiat, napis) && next != null) {
            next.handle(pierwszy, drugi, swiat, napis);
        }
    }

    protected abstract boolean tryHandle(Organizm pierwszy, Organizm drugi, Swiat swiat, Napis napis);
}
