class GameObject {
    private Integer gold;
    private String race;

    public Integer getGold() {
        return gold;
    }

    public String getRace() {
        return race;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "gold=" + gold +
                ", race='" + race + '\'' +
                '}';
    }

    public GameObject(Integer gold, String race) {
        this.gold = gold;
        this.race = race;
    }
}
