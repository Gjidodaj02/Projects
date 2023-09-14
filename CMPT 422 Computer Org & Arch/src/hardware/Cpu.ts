import { Hardware } from "./Hardware";
import { ClockListener } from "./imp/ClockListener";

export class Cpu extends Hardware implements ClockListener{

    private cpuClockCount : number = 0;

    constructor() {


        super(0, "Cpu1");
       
    }//constructor()

    //increment clock count
    //& show pulse receives with count
    pulse(): void {

        this.cpuClockCount++;

        super.log("received clock pulse - CPU Clock Count: " + this.cpuClockCount);

    }//pulse

}//Cpu

