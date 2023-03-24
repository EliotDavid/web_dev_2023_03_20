interface ResponseDto {
   
    board: {
        boardContent: string,
        boardImgUrl: string,
        boardNumber: 0,
        boardTitle: string,
        boardWriteDatetime: string,
        commentCount: 0,
        likeCount: 0,
        viewCount: 0,
        writerEmail: string,
        writerNickname: string,
        writerProfileUrl: string
      },
      commentList: [
        {
          boardNumber: 0,
          commentContent: string,
          commentNumber: 0,
          writeDatetime: string,
          writerEmail: string,
          writerNickname: string,
          writerProfileUrl: string
        }
      ],
      likeList: [
        {
          boardNumber: 0,
          userEmail: string,
          userNickname: string,
          userProfileUrl: string
        }
      ]

    }
    
export default ResponseDto;
