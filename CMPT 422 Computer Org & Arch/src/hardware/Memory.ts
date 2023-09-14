import { Hardware } from "./Hardware";
import { ClockListener } from "./imp/ClockListener";

export class Memory extends Hardware implements ClockListener{

    private theArray : number[] = new Array(0xFFFF);
    
    constructor() {

        super(0, "RAM1");
       
    }//constructor()

    //Initalize each element in array to 0x00
    memoryInitialization(): any {
        
        for (let i = 0x00; i < this.theArray.length; i++)
        {

            this.theArray[i] = 0x00;

        }//for

    }//memoryInitializtion()

    //print the hex message
    displayHex(hexValue): any {

        //if input value is too large for the memory, return error
        if (hexValue > this.theArray.length){
            
            console.log("Address: " + hexValue.toString(16) + " Contains Value: ERR [hexValue convertion]: number undefined");
       
        }//if

        //else, print to 0x14
        else{

            for (let i = 0x00; i < 0x14; i++) {

                super.log("Address: " + this.hexLog(i, 4) + " Contains Value: " + this.hexLog(this.theArray[i], 2));
            
            }//console.log()

        }//else

    }//displayHex()

    //show pulse was received
    pulse(): void {

        super.log("received clock pulse");

    }//pulse

}//Hardware