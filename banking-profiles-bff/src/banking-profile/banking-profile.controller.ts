import { Body, Controller, Get, Param, Post, UseGuards } from '@nestjs/common';
import { BankingProfileService } from './banking-profile.service';
import { AuthGuard } from '@nestjs/passport';

@Controller('banking-profiles')
@UseGuards(AuthGuard('jwt'))
export class BankingProfileController {
  constructor(private readonly bankingProfileService: BankingProfileService) {}

  @Get(':encryptedUniqueCode')
  async getBankingProfile(@Param('encryptedUniqueCode') encryptedUniqueCode: string) {
    return this.bankingProfileService.getBankingProfile(encryptedUniqueCode);
  }

  @Post('/encrypt')
  async encryptValue(@Body() body: {value: string}) {
    return this.bankingProfileService.encrypt(body.value);
  }

  @Post('/decrypt')
  async decryptValue(@Body() body: {value: string}) {
    return this.bankingProfileService.decrypt(body.value);
  }
}
