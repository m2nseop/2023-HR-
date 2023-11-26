package com.gg.compJob;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gg.indvInfo.IndvInfoDTO;
import com.gg.indvInfo.IndvInfoService;

import scala.collection.JavaConverters;
import scala.collection.Seq;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;

@CrossOrigin
@RestController
public class CompJobController {
    @Autowired
    private CompJobService compJobService;

    @Autowired
    private IndvInfoService indvInfoService;

    @GetMapping("/get-comp-job-list")
    public List<CompJobDTO> getCompJobList() {
        System.out.println("***CompJob Controller..***");
        System.out.println();
        List<CompJobDTO> compJobList = compJobService.getCompJobList();
        // System.out.println("compJobList: " + compJobList);
        System.out.println("comp job list");
        return compJobList;
    }

    @GetMapping("/get-candidate")
    public List<IndvInfoDTO> getCandidateList(String comp_id) {
        System.out.println("comp_id: " + comp_id);
        CompJobDTO dto = compJobService.getCompJob(Integer.parseInt(comp_id));

        System.out.println("***CompJob Controller..***");
        System.out.println();
        System.out.println("< 구인 공고문 키워드 추출 전 >\n\n" + dto.getB() + "\n");
        String text = dto.getB();

        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);

        // Tokenize
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);

        List<KoreanTokenJava> tokenList = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);

        // Phrase Extraction
        List<KoreanPhraseExtractor.KoreanPhrase> phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true,
                true);

        // System.out.println("******추출 테스트********");
        // System.out.println("\n\ntokens 확인");
        // System.out.println(tokens);

        // System.out.println("\n\ntokens List 확인");
        // System.out.println(tokenList);

        // System.out.println("\n\n명사 분리 확인");
        // System.out.println(phrases);

        // System.out.println("\n\n최종 단어 분리 확인");

        // Convert Seq to List
        List<KoreanTokenizer.KoreanToken> totalTokenList = JavaConverters.seqAsJavaList(tokens);

        // Extract Nouns, Alpha, Hashtag
        List<String> nounsList = new ArrayList<>();
        // StringBuilder nouns = new StringBuilder();
        List<String> alphas = new ArrayList<>();
        List<String> hashtags = new ArrayList<>();

        for (KoreanTokenizer.KoreanToken token : totalTokenList) {
            String pos = token.pos().toString();
            String textValue = token.text();

            if (pos.startsWith("N")) {
                // nouns.append(textValue).append("* ");
                nounsList.add(textValue);
            } else if (pos.equals("Alpha")) {
                alphas.add(textValue);
            } else if (pos.equals("Hashtag")) {
                hashtags.add(textValue);
            }
        }
        // if (nouns.length() > 0) {
        // nouns.setLength(nouns.length() - 1);
        // }
        List<String> distincetNouns = nounsList.stream().distinct().collect(java.util.stream.Collectors.toList());
        StringBuilder nouns = new StringBuilder();
        for (String str : distincetNouns) {
            nouns.append(str).append("* ");
        }

        // String jsonList = joinList(nouns);
        System.out.println("< 구인 공고문 키워드 추출 후 >\n\n" + nouns + "\n");

        // List<IndvInfoDTO> indvInfoList = indvInfoService.searchWithKeywords(nouns);
        // List<IndvInfoDTO> indvInfoList =
        // indvInfoService.searchWithKeywords(nouns.toString());
        System.out.println("< 구직자 매칭 결과 >\n");
        List<IndvInfoDTO> indvInfoList = indvInfoService.searchWithKeywords(nouns.toString());
        int index = 1;
        for (IndvInfoDTO indvInfo : indvInfoList) {
            System.out.println(indvInfo);
            System.out.println(index + ".");
            System.out.println("[ 이름 ] " + indvInfo.getC());
            System.out.println("[ 전문분야 ] " + indvInfo.getI());
            System.out.println("[ 학력 ] " + indvInfo.getG());
            System.out.println("[ 경력 ] " + indvInfo.getH());
            System.out.println("[ 적합도 ] " + indvInfo.getScore());
            System.out.println("[ 이메일] " + indvInfo.getF());
            System.out.println("\n");
            index++;
        }
        // System.out.println("indvInfoList: " + indvInfoList);
        return indvInfoList;
    }

    // @GetMapping("/get-candidate-detail"){

    // }

}
