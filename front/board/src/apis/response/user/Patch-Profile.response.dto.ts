interface ResponseDto {
    address : string;
    email : string;
    expiredTime : number;
    nickname : string;
    profile : string | null; // string 또는 null로 올수 있다는 의미
    telNumber : string;
    token : string;
}

export default ResponseDto;