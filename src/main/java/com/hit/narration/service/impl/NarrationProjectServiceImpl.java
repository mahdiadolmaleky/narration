package com.hit.narration.service.impl;

import com.hit.narration.config.Constants;
import com.hit.narration.domain.NarrationProject;
import com.hit.narration.domain.enumeration.SpeakingSpeedEnum;
import com.hit.narration.repository.NarrationProjectRepository;
import com.hit.narration.service.NarrationProjectService;
import com.hit.narration.service.dto.AttachmentDTO;
import com.hit.narration.service.dto.NarrationProjectDTO;
import com.hit.narration.service.mapper.NarrationProjectMapper;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
@Transactional
public class NarrationProjectServiceImpl implements NarrationProjectService {

    private final NarrationProjectRepository narrationProjectRepository;

    private final NarrationProjectMapper narrationProjectMapper;

    public NarrationProjectServiceImpl(NarrationProjectRepository narrationProjectRepository, NarrationProjectMapper narrationProjectMapper) {
        this.narrationProjectRepository = narrationProjectRepository;
        this.narrationProjectMapper = narrationProjectMapper;
    }

    @Override
    public NarrationProjectDTO save(NarrationProjectDTO narrationProjectDTO) {
        NarrationProject narrationProject = narrationProjectMapper.toEntity(narrationProjectDTO);
        narrationProject = narrationProjectRepository.save(narrationProject);
        return narrationProjectMapper.toDto(narrationProject);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NarrationProjectDTO> findAll(Pageable pageable) {
        return narrationProjectRepository.findAll(pageable)
                .map(narrationProjectMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NarrationProjectDTO> findOne(Long id) {
        return narrationProjectRepository.findById(id)
                .map(narrationProjectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        narrationProjectRepository.deleteById(id);
    }

    private Integer getWordsCount(AttachmentDTO attachmentDTO) throws IOException, OpenXML4JException, XmlException {

        String text = null;
        int wordsCount = 0;
        File file = byteTOFile(attachmentDTO.getData());

        switch (attachmentDTO.getDataContentType()) {
            case "docx":
                XWPFDocument docx = new XWPFDocument(new FileInputStream(file));
                XWPFWordExtractor we = new XWPFWordExtractor(docx);
                text = we.getText();
                wordsCount = stringTokenizer(text);
                break;
            case "pptx":
                XSLFPowerPointExtractor powerPointExtractor = new XSLFPowerPointExtractor(OPCPackage.open(new FileInputStream(file)));
                text = powerPointExtractor.getText();
                wordsCount = stringTokenizer(text);
                break;
            case "pdf":
                PDDocument document = PDDocument.load(file);
                PDFTextStripper pdfStripper = new PDFTextStripper();
                text = pdfStripper.getText(document);
                wordsCount = stringTokenizer(text);
                document.close();
                break;
            case "txt":
                BufferedReader br = new BufferedReader(new FileReader(file));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    text = sb.toString();

                    wordsCount = stringTokenizer(text);
                } finally {
                    br.close();
                }
                break;
        }

        return wordsCount;
    }

    //  Calculate the speaking time
    private Double speakingTime(int wordsCount, SpeakingSpeedEnum speakingSpeed) {

        Double speakingTime = 0.0;

        switch (speakingSpeed) {
            case slow:
                speakingTime = wordsCount * Constants.slow_speed;
                break;
            case average:
                speakingTime = wordsCount * Constants.avg_speed;
                break;
            case fast:
                speakingTime = wordsCount * Constants.fast_speed;
                break;
        }
        return speakingTime / 60;
    }

    //  Calculate the number of words
    private int stringTokenizer(String text) {
        StringTokenizer tokens = new StringTokenizer(text);
        int wordsCount = tokens.countTokens();
        return wordsCount;
    }

    private File byteTOFile(byte[] bytes) {
        File file = null;
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return file;
    }
}
