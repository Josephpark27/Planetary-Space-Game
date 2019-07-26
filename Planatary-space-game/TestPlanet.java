public class TestPlanet {
  public static void main (String[] args){
    Planet Michaelurn = new Planet(1, 2, 12, 15, 50, "jupiter.gif");
    Planet Josephiter = new Planet(4, 23, 14, 22, 500, "jupiter.gif");
    System.out.println(Michaelurn.calcForceExertedBy(Josephiter));
  }
}
