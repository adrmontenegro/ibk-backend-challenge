import { Injectable } from '@nestjs/common';
import * as crypto from 'crypto';

// Encryption Configuration 
// Load the key and IV from environment variables (or a secret management system)
const algorithm = 'aes-256-cbc';

@Injectable()
export class CryptoUtils {

  private getEncryptionConfig(): { key: Buffer, iv: Buffer } {
    const encryptionKey = process.env.ENCRYPTION_KEY;
    const iv = process.env.ENCRYPTION_IV;

    if (!encryptionKey || !iv) {
      throw new Error('Encryption key and IV are required. Set them as environment variables.');
    }

    return {
      key: Buffer.from(encryptionKey, 'hex'),
      iv: Buffer.from(iv, 'hex'),
    };
  }

  encrypt(value: string): string {
    const { key, iv } = this.getEncryptionConfig(); 
    const cipher = crypto.createCipheriv(algorithm, key, iv);
    let encrypted = cipher.update(value, 'utf8', 'hex');
    encrypted += cipher.final('hex');
    return encrypted;
  }

  decrypt(value: string): string {
    const { key, iv } = this.getEncryptionConfig(); 
    const decipher = crypto.createDecipheriv(algorithm, key, iv);
    let decrypted = decipher.update(value, 'hex', 'utf-8');
    decrypted += decipher.final('utf-8');
    return decrypted;
  }
}
