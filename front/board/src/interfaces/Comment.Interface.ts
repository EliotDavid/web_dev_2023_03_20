interface Comment {
    commentNumber : number;
    writerEmail : string;
    boardNumber : number;
    writeDateTime : string;
    commentContent : string;
    writerProfileUrl? : string | null;
    writerNickname : string;

}

export default Comment;
