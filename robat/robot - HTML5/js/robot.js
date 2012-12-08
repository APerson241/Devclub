function Robot() {
    this.body;
    this.powerSource;
    this.hardDrive;
}
Robot.prototype.attach = function(part, joint) {
    body.attach(part, joint);
};
Robot.prototype.canLearn = function(task) {
    if(this.hardDrive.memory >= task.memory) {
        for(i=0; i<task.required.length; i++) {
            if(!this.attachments.contains()) {
                return false;
            }
        }
        return true;
    }
};

function Part(name, description, value, imageURL) {
    this.name = name;
    this.description = description;
    this.value = value;
    this.image = new Image();
    this.image.src = imageURL;
}

function HardDrive() {
    Part.call(this);
    this.knol = [];
    this.memory;
}
HardDrive.prototype = new Part(name, description, value, imageURL);
HardDrive.prototype.constructor = HardDrive;

function PowerSource() {
    Part.call(this);
    this.power; //In watts.
    this.efficiency;
}
PowerSource.prototype = new Part(name, description, value, imageURL);
PowerSource.prototype.constructor = PowerSource;

function Body() {
    Part.call(this);
    this.attachments = [];
}
Body.prototype = new Part(name, description, value, imageURL);
Body.prototype.constructor = Body;
Body.prototype.attach = function(part, joint) {
    
};

function Attachable() {
    Part.call(this);
}
Attachable.prototype = new Part(name, description, value, imageURL);
Attachable.prototype.constructor = Attachable;