interface User {

    email : string;

    // 패스워드는 프론트엔드에서 받지않음

    nickname : string;
    telNumber : string;
    address : string;
    profile? : string | null; // 이 ? 하면 필드가 존재할수도 있고 존재하지 않을수도 있다는 의미로 필수가 아니다라는 의미라고 함


}

export default User;

