package cn.hutool.crypto.symmetric;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * 见：https://stackoverflow.com/questions/32672241/using-bouncycastles-chacha-for-file-encryption
 */
public class ChaCha20Test {

	@Test
	public void encryptAndDecryptTest() {
		// 32 for 256 bit key or 16 for 128 bit
		final byte[] key = RandomUtil.randomBytes(32);
		// 64 bit IV required by ChaCha20
		final byte[] iv = RandomUtil.randomBytes(12);

		final ChaCha20 chacha = new ChaCha20(key, iv);

		final String content = "test中文";
		// 加密为16进制表示
		final String encryptHex = chacha.encryptHex(content);
		// 解密为字符串
		final String decryptStr = chacha.decryptStr(encryptHex, CharsetUtil.UTF_8);

		Assert.assertEquals(content, decryptStr);
	}
}