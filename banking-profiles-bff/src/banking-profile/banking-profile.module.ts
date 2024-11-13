import { Module } from '@nestjs/common';
import { BankingProfileService } from './banking-profile.service';
import { BankingProfileController } from './banking-profile.controller';
import { HttpModule } from '@nestjs/axios';
import { CryptoUtils } from '../utils/cryptoUtils';

@Module({
  imports: [HttpModule], // For making HTTP requests
  providers: [BankingProfileService, CryptoUtils],
  controllers: [BankingProfileController],
})
export class BankingProfileModule {}
