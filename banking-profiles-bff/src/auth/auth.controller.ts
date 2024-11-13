import { Body, ClassSerializerInterceptor, Controller, Post, UnauthorizedException, UseInterceptors } from '@nestjs/common';
import { AuthService } from './auth.service';
import { CreateUserDto } from 'src/user/dto/create-user.dto';

@Controller('auth')
@UseInterceptors(ClassSerializerInterceptor)
export class AuthController {
  constructor(private readonly authService: AuthService) {}


  @Post('login')
  async login(@Body() userDto: CreateUserDto) {
    const user = await this.authService.validateUser(userDto.username, userDto.password);
    if(!user) {
      throw new UnauthorizedException();
    }
    return this.authService.loginUser(user);
  }

  @Post('register')
  async register(@Body() userDto: CreateUserDto) {
    return this.authService.registerUser(userDto);
  }
}
