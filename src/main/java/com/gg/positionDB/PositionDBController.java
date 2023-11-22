package com.gg.positionDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import scala.collection.JavaConverters; // JavaConverters를 사용하기 위해 임포트
import scala.collection.Seq;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;

// list를 쉼표로 구분된 문자열로 변환
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

@CrossOrigin
@RestController
public class PositionDBController {
	@Autowired
	PositionDBService service;

	@PostMapping("/register-position")
	public void InsertPos(@RequestBody PositionDBDTO dto) {
		System.out.println("***Pos Controller..***");
		System.out.println("   pos.posNO   : " + dto.getPOST_NO());
		System.out.println("   pos.co_id    : " + dto.getCO_ID());
		System.out.println("   pos.position    : " + dto.getPOSITION());
		System.out.println("   pos.qualify    : " + dto.getQUALIFY());
		System.out.println("   pos.job    : " + dto.getJOB());
		System.out.println("   pos.EMPLOY_TYPE     : " + dto.getEMPLOY_TYPE());
		System.out.println("   pos.REGI_DT    : " + dto.getREGI_DT());
		System.out.println("   pos.CO_NO    : " + dto.getCO_NO());

		// 직무설명 문구 테스트
		System.out.println("************");
		System.out.println(dto.getJOB());

		System.out.println("************2");
		String text = dto.getJOB();
		System.out.println(text);

		// Normalize
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(text);

		// Tokenize
		Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);

		List<KoreanTokenJava> tokenList = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens);

		// Phrase Extraction
		List<KoreanPhraseExtractor.KoreanPhrase> phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true,
				true);

		System.out.println("******추출 테스트********");
		System.out.println("\n\ntokens 확인");
		System.out.println(tokens);

		System.out.println("\n\ntokens List 확인");
		System.out.println(tokenList);

		System.out.println("\n\n명사 분리 확인");
		System.out.println(phrases);

		System.out.println("\n\n최종 단어 분리 확인");

		// Convert Seq to List
		List<KoreanTokenizer.KoreanToken> totalTokenList = JavaConverters.seqAsJavaList(tokens);

		// Extract Nouns, Alpha, Hashtag
		List<String> nouns = new ArrayList<>();
		List<String> alphas = new ArrayList<>();
		List<String> hashtags = new ArrayList<>();

		for (KoreanTokenizer.KoreanToken token : totalTokenList) {
			String pos = token.pos().toString();
			String textValue = token.text();

			if (pos.startsWith("N")) {
				nouns.add(textValue);
			} else if (pos.equals("Alpha")) {
				alphas.add(textValue);
			} else if (pos.equals("Hashtag")) {
				hashtags.add(textValue);
			}
		}

		// Print extracted Tokens
		// System.out.println("Extracted Nouns: " + nouns);
		// System.out.println("Extracted Alphas: " + alphas);
		// System.out.println("Extracted Hashtags: " + hashtags);

		String jsonList = String.join(",", nouns);
		System.out.println("jsonList: " + jsonList);
		dto.setJOB_KEY(jsonList); // 변경한 데이터 저장

		service.insertPos(dto);
	}

}
