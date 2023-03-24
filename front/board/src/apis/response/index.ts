export interface ResponseDto<Data> { 
    result : boolean;
    message : string;
    data : Data;
}