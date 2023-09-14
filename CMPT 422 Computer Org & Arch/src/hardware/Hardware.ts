export class Hardware{

    constructor(public ID: number, public Name: string, public debug: boolean = true) {
      
    }//constructor
    
    //print message
    log(message) {

        if (this.debug == true) {

            let date = new Date().getTime();
            let messageOut: string = "[HW - " + this.Name + " id: " + this.ID + " - " + date + " ]: " + message;
            console.log(messageOut);

        }//if

    }//log()
    
    //convert & return printable hex
    hexLog(num: number, len: number): string {

        let hexNum = num.toString(16);
        let padding = len - hexNum.length;
        
        for (let i = 0; i < padding; i++){
            
            hexNum = "0" + hexNum;

        }//for

        return hexNum;

    }//hexLog

}//Hardware

// https://www.typescriptlang.org/docs/handbook/classes.ht