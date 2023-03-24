interface RequestDto {
    boardNumber : number; // 타입스크립트의 정수타입은 number임
    boardTitle : string;
    boardContent : string;
    boardImgUrl : string | null;
}

export default RequestDto;