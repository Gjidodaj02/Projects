import { Hardware } from "./Hardware";
import { System } from "../System";
import { Cpu } from "./Cpu";
import { Memory } from "./Memory";
import { ClockListener } from "./imp/ClockListener";

export class Clock extends Hardware{

    private clockArray: Array<ClockListener> = new Array();

    constructor() {

        super(0, "Clock");

    }//constructor

    //set the array
    setArray(x) {

        this.clockArray.push(x);

    }//setArray

    //start/set the time interval for the clock
    startClock(interval) {

        setInterval(() => {
            for (let i = 0; i < this.clockArray.length; i++) {

                this.clockArray[i].pulse();

            }//for
        }, interval)//setInterval
    }//startClock

}//Clock
