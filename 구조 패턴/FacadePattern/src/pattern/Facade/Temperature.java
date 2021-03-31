package pattern.Facade;

//파사드
public class Temperature {
    public Thermometer station;

    public Temperature(Thermometer station) {
        this.station = station;
    }

    public float getTemp(){
        // 인스턴스 저장 (불필요한 호출)
        Thermometer thermometer = this.getThermometer();
        //  인스턴스를 통해 메서드 실행
        return thermometer.getTemperature();
    }
    // 불필요함
    private Thermometer getThermometer() {
        return station;
    }
}
