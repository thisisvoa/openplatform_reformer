//package cn.com.reformer.common.util;
//
//
//import com.iflytek.cloud.speech.*;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//
///**
// * 功能描述:
// * <p/>
// * 版权所有：杭州立方控股
// * <p/>
// * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
// *
// * @author jiqinwei 新增日期：2016/9/22
// * @author jiqinwei 修改日期：2016/9/22
// * @version 1.0.0
// * @since 1.0.0
// */
//public class WordToVoice {
//
//
//    public static void main(String[] args) throws Exception{
//        System.out.println(System.getProperty("java.library.path"));
//        WordToVoice voice = new WordToVoice();
//        Word2Voice("1234567890abcdefg", "D:\\QWE.pcm");
//        System.exit(0);
//    }
//
//    public static void Word2Voice(String text , String path) throws Exception{
//        SpeechUtility.createUtility(SpeechConstant.APPID + "=57e25b72");//一个sdk一个appid
//        //1.创建SpeechSynthesizer对象
//        SpeechSynthesizer mTts  = SpeechSynthesizer.createSynthesizer();
//        //2.合成参数设置
//        mTts.setParameter(SpeechConstant.VOICE_NAME,"xiaoyan");//这是发音人
//        mTts.setParameter(SpeechConstant.SPEED,"50");//设置语速，范围0-100
//        mTts.setParameter(SpeechConstant.PITCH,"50");//设置语调，范围0-100
//        mTts.setParameter(SpeechConstant.VOLUME,"100");//设置音量，范围0-100
//
//        SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {
//            @Override
//            public void onBufferProgress(int i) {
//
//            }
//
//            @Override
//            public void onSynthesizeCompleted(String s, SpeechError speechError) {
//                try {
//                    convertAudioFiles(s,s.replace("pcm","wav"));
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        };
//        //3.开始合成
//        //设置合成音频保存位置（可自定义位置，），默认保存在""
//        mTts.synthesizeToUri(text, path, synthesizeToUriListener);
//        Thread.sleep(3000);
//    }
//
//    private static void convertAudioFiles(String src, String target) throws Exception {
//        FileInputStream fis = new FileInputStream(src);
//        FileOutputStream fos = new FileOutputStream(target);
//        //计算长度
//        byte[] buf = new byte[1024 * 4];
//        int size = fis.read(buf);
//        int PCMSize = 0;
//        while (size != -1) {
//            PCMSize += size;
//            size = fis.read(buf);
//        }
//        fis.close();
//        //填入参数，比特率等等。这里用的是16位单声道 8000 hz
//        WaveHeader header = new WaveHeader();
//        //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
//        header.fileLength = PCMSize + (44 - 8);
//        header.FmtHdrLeth = 16;
//        header.BitsPerSample = 16;
//        header.Channels = 1;
//        header.FormatTag = 0x0001;
//        header.SamplesPerSec = 8000;
//        header.BlockAlign = (short)(header.Channels * header.BitsPerSample / 8);
//        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
//        header.DataHdrLeth = PCMSize;
//
//        byte[] h = header.getHeader();
//
//        assert h.length == 44; //WAV标准，头部应该是44字节
//        //write header
//        fos.write(h, 0, h.length);
//        //write data stream
//        fis = new FileInputStream(src);
//        size = fis.read(buf);
//        while (size != -1) {
//            fos.write(buf, 0, size);
//            size = fis.read(buf);
//        }
//        fis.close();
//        fos.close();
//        System.out.println("Convert OK!");
//    }
//
//}
