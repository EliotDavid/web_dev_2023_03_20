import { Dispatch, SetStateAction, useState } from "react";
import {
  Box,
  Button,
  Typography,
  TextField,
  FormControl,
  InputLabel,
  Input,
  InputAdornment,
  IconButton,
} from "@mui/material";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Visibility from "@mui/icons-material/Visibility";
import KeyboardArrowRightIcon from '@mui/icons-material/KeyboardArrowRight';

import { useSignUpStore } from 'src/stores';
import axios from "axios";
import { SignUpDto } from "src/apis/request/auth";
import ResponseDto from "src/apis/response";
import { SignUpResponseDto } from "src/apis/response/auth";
import { SIGN_UP_URL } from "src/constants/api";

function FirstPage() {

  const { email, password, passwordCheck } = useSignUpStore();
  const { setEmail, setPassword, setPasswordCheck } = useSignUpStore();

  const [showPassword, setShowPassword] = useState<boolean>(false);
  const [showPasswordCheck, setShowPasswordCheck] = useState<boolean>(false);

  return (
    <Box>
      <TextField
        sx={{ mt: "40px" }}
        fullWidth
        label="이메일 주소*"
        variant="standard"
        value={email}
        onChange={(event) => setEmail(event.target.value)}
      />
      <FormControl fullWidth variant="standard" sx={{ mt: "40px" }}>
        <InputLabel>비밀번호*</InputLabel>
        <Input
          type={showPassword ? "text" : "password"}
          endAdornment={
            <InputAdornment position="end">
              <IconButton onClick={() => setShowPassword(!showPassword)}>
                {showPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          }
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
      </FormControl>
      <FormControl fullWidth variant="standard" sx={{ mt: "40px" }}>
        <InputLabel>비밀번호 확인*</InputLabel>
        <Input
          type={showPasswordCheck ? "text" : "password"}
          endAdornment={
            <InputAdornment position="end">
              <IconButton
                onClick={() => setShowPasswordCheck(!showPasswordCheck)}
              >
                {showPasswordCheck ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            </InputAdornment>
          }
          value={passwordCheck}
          onChange={(event) => setPasswordCheck(event.target.value)}
        />
      </FormControl>
    </Box>
  );
}

function SecondPage() {

  const { nickname, telNumber, address, addressDetail } = useSignUpStore();
  const { setNickname, setTelNumber, setAddress, setAddressDetail } = useSignUpStore();

  return (
    <Box>
      <TextField sx={{mt: '40px'}} fullWidth label="닉네임*" variant="standard" value={nickname} onChange={(event) => setNickname(event.target.value)} />
      <TextField sx={{mt: '40px'}} fullWidth label="휴대폰 번호*" variant="standard" value={telNumber} onChange={(event) => setTelNumber(event.target.value)} />
      <FormControl fullWidth variant="standard" sx={{mt: '40px'}}>
        <InputLabel>주소*</InputLabel>
        <Input type="text" endAdornment={
          <InputAdornment position="end">
            <IconButton>
              <KeyboardArrowRightIcon />
            </IconButton>
          </InputAdornment>
        } 
        value={address}
        onChange={(event) => setAddress(event.target.value)}
        />
      </FormControl>
      <TextField sx={{mt: '40px'}} fullWidth label="상세 주소*" variant="standard" value={addressDetail} onChange={(event) => setAddressDetail(event.target.value)} />
    </Box>
  );
}

interface Props {
  setLoginView: Dispatch<SetStateAction<boolean>>;
}

export default function SignUpCardView({ setLoginView }: Props) {

  const [page, setPage] = useState<number>(1);
  const { email, password, passwordCheck } = useSignUpStore();
  const { nickname, telNumber, address, addressDetail } = useSignUpStore();

  const onNextButtonHandler = () => {
    //? 해당 문자열 변수가 빈값인지 확인
    //? 1. 해당 변수 == '';
    //? 2. 해당 변수의 길이 == 0;
    if (!email || !password || !passwordCheck) {
      alert('모든 값을 입력하세요.');
      return;
    }
    if (password !== passwordCheck) {
      alert('비밀번호가 서로 다릅니다.');
      return;
    }
    setPage(2);
  };

  const onSignUpHandler = () => { //함수
    if (!email || !password || !passwordCheck) {
      alert('모든 값을 입력하세요.');
      setPage(1);
      return;
    }
    if (!nickname || !telNumber || !address || !addressDetail) {
      alert('모든 값을 입력하세요.');
      setPage(2);
      return;
    }
    if (password !== passwordCheck) {
      alert('비밀번호가 서로 다릅니다.');
      setPage(1);
      return;
    }

    //alert('회원가입 완료!'); // 이건 지운다고 함(2023_03_27에 지웠음)
    
    const data : SignUpDto = { email, password, nickname, telNumber, address : '${address} ${addressDetail}'}; // 이 address 는 일반 address와 addressDetail에 해당하는 걸 모두 포함한다라는 의미임
    console.log(data);

    console.log('axios 이전!!');

    axios.post(SIGN_UP_URL, data)
    .then((response) => {
      const { result, message, data} = response.data as ResponseDto<SignUpResponseDto>;
      if(result) setLoginView(true); 
      else alert(message);
  
    //.then((response) => {console.log('Success');
    //.then((response) => {console.log(response);
    })
    .catch((error) => {console.log(error.response.status); // 만약에 400번대 에러가 떴다고 하면 400을 받을 수 있음(무슨 말이지?) 
    //.catch((error) => {console.log(error.message); 
    });

    //const response = await axios.post("http://localhost:4040/auth/sign-up", data); // await을 걸면 빨간줄이 그어지는데 await은 맑드대로 기다린다는 의미이고 비동기와 관련된 작업인데 동기처리로 바꾸겠다는 뜻임(post안의 작업들이 끝날때까지 기다리고라는 의미임)
                                                                                  // onSignUpHandelr에다가 () 앞에 async라고 붙임

    console.log('axios 이후!!')

    //axios.post("http://localhost:4040/auth/sign-up", data).then().catch(); // 이 axios를 쓰면 // 이 axios로 어떤 메서드로 보낼 // 첫번째(string): 어떠한 주소로 보낼것이냐, 두번째(data) : requstBody에 들어가는 데이터를 데이터자리에 넣어줌 / 세번째: Header에 추가해야되는것들을 
    // axios.post().then((response) => {}).catch((error) => {});  : 코드가 실행되는데 밀리언초가 걸리지 않고 바로 실행됨  // 의미 : 한 작업의 결과가 반환이되면 그 작업을 반환할적에는 then 안에서 실행해라는 의미임 그러다가 반환을 하는데 에러가 있따면 그 에러는 catch안에서 잡는다는 의미임
    // 작업의 결과는 then에서 처리
    // post방식이기 때문에 requestbody에 데이터를 포함시킬 수 있음
    console.log(data);

  }

  return (
    <Box
      display="flex"
      sx={{
        height: "100%",
        flexDirection: "column",
        justifyContent: "space-between",
      }}
    >
      <Box>
        <Box display="flex" sx={{ justifyContent: "space-between" }}>
          <Typography variant="h5" fontWeight="900">
            회원가입
          </Typography>
          <Typography variant="h5" fontWeight="900">
            {page}/2
          </Typography>
        </Box>
        {page === 1 ? <FirstPage /> : <SecondPage />}
      </Box>
      <Box>
        {page === 1 && (
          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{ mb: "20px" }}
            onClick={onNextButtonHandler}
          >
            다음 단계
          </Button>
        )}
        {page === 2 && (
          <Button
            fullWidth
            variant="contained"
            size="large"
            sx={{ mb: "20px" }}
            onClick={onSignUpHandler}
          >
            회원가입
          </Button>
        )}
        <Typography textAlign="center">
          이미 계정이 있으신가요?
          <Typography
            component="span"
            fontWeight={900}
            onClick={() => setLoginView(true)}
          >
            {" "}
            로그인
          </Typography>
        </Typography>
      </Box>
    </Box>
  );
}
