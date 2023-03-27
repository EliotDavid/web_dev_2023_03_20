import { Dispatch, SetStateAction, useState } from 'react'
import { Box, TextField, Typography, FormControl, InputLabel, Input, InputAdornment, IconButton, Button } from '@mui/material';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Visibility from '@mui/icons-material/Visibility';

import { USER } from 'src/mock';
import { useUserStore } from 'src/stores';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { SIGN_IN_URL } from 'src/constants/api';
import { SignInDto } from 'src/apis/request/auth';
import ResponseDto from 'src/apis/response';
import { SignInResponseDto } from 'src/apis/response/auth';
import { useCookies } from 'react-cookie';

interface Props {
    setLoginView: Dispatch<SetStateAction<boolean>>
}

export default function LoginCardView({ setLoginView }: Props) {

  const [cookies, setCookie] = useCookies();  

  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [showPassword, setShowPassword] = useState<boolean>(false);
  
  const { setUser } = useUserStore();

  const navigator = useNavigate();

  const onLoginHandler = () => {
    //? email 입력했는지 검증 / password 입력했는지 검증
    if (!email.trim() || !password) {
        alert('모든 값을 입력해주세요.');
        return;
    }
    

    const data : SignInDto = {email, password}; // 로그인 작업이기 떄문에 이메일과 비밀번호만 있으면 됨
    axios.post(SIGN_IN_URL, data).then((response) => { // 회원가입도 post방식이기 때문에 post지정해주고 그러고나서 성공시에 작업은 then이 처리해주고 
        const {result, message, data} = response.data as ResponseDto<SignInResponseDto>;
        // 정상적인 통신이 왔따면
        if(result && data) {
        const{ token, expiredTime, ...user } = data; // token, expiredTime : 비구조화할당 ...user : 나머지를 user로 묶겠다는 뜻    
        //? 로그인 처리
        //? 쿠키에 로그인 데이터 (Token) 보관 
        const now = new Date().getTime();
        const expires = new Date(now + expiredTime);
        setCookie('accessToken', token, { expires });
        //? 스토어에 유저 데이터 보관


        setUser(user); // 위에서 user를 여기에 넣음
        navigator('/');
        } else { 
            alert('로그인정보가 잘못되었습니다!');
        }
    }).catch((error) => { // 실패시 작업은 catch가 잡아줌
        console.log(error); // 예외사항에 대한 메세지를 띄우도록 함
    }); 
    
    //? USER mock 데이터의 email과 password가 입력받은 email과 password와 일치하는지 검증
    //if (USER.email !== email || USER.password !== password) {
        //alert('로그인 정보가 일치하지 않습니다.');
        //return;
    //}

  }

  return (
    <Box display='flex' sx={{height: '100%', flexDirection: 'column', justifyContent: 'space-between'}}>
        <Box>
            <Typography variant='h5' fontWeight='900'>로그인</Typography>
            <TextField sx={{mt: '40px'}} fullWidth label="이메일 주소" variant="standard" onChange={(event) => setEmail(event.target.value)} />
            <FormControl fullWidth variant="standard" sx={{ mt: '40px' }}>
                <InputLabel>비밀번호</InputLabel>
                <Input
                    type={showPassword ? 'text' : 'password'}
                    endAdornment={
                    <InputAdornment position="end">
                        <IconButton
                        onClick={() => setShowPassword(!showPassword)}
                        >
                        {showPassword ? <VisibilityOff /> : <Visibility />}
                        </IconButton>
                    </InputAdornment>
                    }
                    onChange={(event) => setPassword(event.target.value)}
                />
            </FormControl>
        </Box>
        <Box>
            <Button sx={{mb: '20px'}} fullWidth variant="contained" size='large' onClick={onLoginHandler}>로그인</Button>
            <Typography textAlign={'center'}>신규사용자 이신가요? 
                <Typography component='span' fontWeight={900} onClick={() => setLoginView(false)}> 회원가입</Typography>
            </Typography>
        </Box>
    </Box>
  )
}
