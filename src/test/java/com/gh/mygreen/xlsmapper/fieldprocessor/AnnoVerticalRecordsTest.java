package com.gh.mygreen.xlsmapper.fieldprocessor;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static com.gh.mygreen.xlsmapper.TestUtils.*;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.util.CellReference;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.gh.mygreen.xlsmapper.AnnotationInvalidException;
import com.gh.mygreen.xlsmapper.IsEmptyBuilder;
import com.gh.mygreen.xlsmapper.IsEmptyComparator;
import com.gh.mygreen.xlsmapper.IsEmptyConfig;
import com.gh.mygreen.xlsmapper.POIUtils;
import com.gh.mygreen.xlsmapper.Utils;
import com.gh.mygreen.xlsmapper.XlsMapper;
import com.gh.mygreen.xlsmapper.annotation.OverRecordOperate;
import com.gh.mygreen.xlsmapper.annotation.RecordTerminal;
import com.gh.mygreen.xlsmapper.annotation.RemainedRecordOperate;
import com.gh.mygreen.xlsmapper.annotation.XlsBooleanConverter;
import com.gh.mygreen.xlsmapper.annotation.XlsColumn;
import com.gh.mygreen.xlsmapper.annotation.XlsConverter;
import com.gh.mygreen.xlsmapper.annotation.XlsDateConverter;
import com.gh.mygreen.xlsmapper.annotation.XlsFormula;
import com.gh.mygreen.xlsmapper.annotation.XlsHint;
import com.gh.mygreen.xlsmapper.annotation.XlsIsEmpty;
import com.gh.mygreen.xlsmapper.annotation.XlsMapColumns;
import com.gh.mygreen.xlsmapper.annotation.XlsNestedRecords;
import com.gh.mygreen.xlsmapper.annotation.XlsSheet;
import com.gh.mygreen.xlsmapper.annotation.XlsVerticalRecords;
import com.gh.mygreen.xlsmapper.cellconvert.TypeBindException;
import com.gh.mygreen.xlsmapper.fieldprocessor.CellNotFoundException;
import com.gh.mygreen.xlsmapper.fieldprocessor.processor.VerticalRecordsProcessor;
import com.gh.mygreen.xlsmapper.validation.SheetBindingErrors;

/**
 * {@link VerticalRecordsProcessor}のテスタ
 * アノテーション{@link XlsVerticalRecords}のテスタ。
 * 
 * @version 1.5
 * @since 0.5
 * @author T.TSUCHIE
 *
 */
public class AnnoVerticalRecordsTest {
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    @Before
    public void setUp() throws Exception {
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    /**
     * 開始位置の指定のテスト
     */
    @Test
    public void test_load_vr_startedPosition() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(StartedPositionSheet.class);
            
            StartedPositionSheet sheet = mapper.load(in, StartedPositionSheet.class, errors);
            
            if(sheet.normalRecords1 != null) {
                assertThat(sheet.normalRecords1, hasSize(2));
                for(NormalRecord record : sheet.normalRecords1) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.normalRecords2 != null) {
                assertThat(sheet.normalRecords2, hasSize(2));
                for(NormalRecord record : sheet.normalRecords2) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.normalRecords3 != null) {
                assertThat(sheet.normalRecords3, hasSize(2));
                for(NormalRecord record : sheet.normalRecords3) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.normalRecords4 != null) {
                assertThat(sheet.normalRecords4, hasSize(2));
                for(NormalRecord record : sheet.normalRecords4) {
                    assertRecord(record, errors);
                }
            }
            
            assertThat(sheet.normalRecords5, is(nullValue()));
            
        }
    }
    
    /**
     * 開始位置の指定のテスト - ラベル指定が不正
     */
    @Test(expected=CellNotFoundException.class)
    public void test_load_vr_startedPosition_error1() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(StartedPositionError1Sheet.class);
            
            StartedPositionError1Sheet sheet = mapper.load(in, StartedPositionError1Sheet.class, errors);
            
            fail();
        }
    }
    
    /**
     * 開始位置の指定のテスト - アドレス指定の書式が不正
     */
    @Test(expected=AnnotationInvalidException.class)
    public void test_load_vr_startedPosition_error2() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(StartedPositionError2Sheet.class);
            
            StartedPositionError2Sheet sheet = mapper.load(in, StartedPositionError2Sheet.class, errors);
            
            fail();
        }
    }
    
    /**
     * 開始位置の指定のテスト - インデックス指定の書式が不正
     */
    @Test(expected=AnnotationInvalidException.class)
    public void test_load_vr_startedPosition_error3() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(StartedPositionError3Sheet.class);
            
            StartedPositionError3Sheet sheet = mapper.load(in, StartedPositionError3Sheet.class, errors);
            
            fail();
        }
    }
    
    /**
     * 終了位置の指定のテスト
     */
    @Test
    public void test_load_vr_endPosition() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(EndPositionSheet.class);
            
            EndPositionSheet sheet = mapper.load(in, EndPositionSheet.class, errors);
            
            if(sheet.normalRecords1 != null) {
                assertThat(sheet.normalRecords1, hasSize(2));
                for(NormalRecord record : sheet.normalRecords1) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.normalRecords2 != null) {
                assertThat(sheet.normalRecords2, hasSize(2));
                for(NormalRecord record : sheet.normalRecords2) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.normalRecords3 != null) {
                assertThat(sheet.normalRecords3, hasSize(2));
                for(NormalRecord record : sheet.normalRecords3) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.normalRecords4 != null) {
                assertThat(sheet.normalRecords4, hasSize(2));
                for(NormalRecord record : sheet.normalRecords4) {
                    assertRecord(record, errors);
                }
            }
            
        }
    }
    
    /**
     * 見出しの空白
     * @since 1.4
     */
    @Test
    public void test_load_vr_headerSpace() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(HeaderSpaceSheet.class);
            
            HeaderSpaceSheet sheet = mapper.load(in, HeaderSpaceSheet.class, errors);
            
            if(sheet.records1 != null) {
                assertThat(sheet.records1, hasSize(2));
                for(HeaderSpaceSheet.UserRecord record : sheet.records1) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.records2 != null) {
                assertThat(sheet.records2, hasSize(2));
                for(HeaderSpaceSheet.UserRecord record : sheet.records2) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.records3 != null) {
                assertThat(sheet.records3, hasSize(2));
                for(HeaderSpaceSheet.UserRecord record : sheet.records3) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.records4 != null) {
                assertThat(sheet.records4, hasSize(2));
                for(HeaderSpaceSheet.UserRecord record : sheet.records4) {
                    assertRecord(record, errors);
                }
            }
            
        }
        
    }
    
    /**
     * カラムの設定テスト
     */
    @Test
    public void test_load_vr_columnSetting() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(ColumnSettingSheet.class);
            
            ColumnSettingSheet sheet = mapper.load(in, ColumnSettingSheet.class, errors);
            
            if(sheet.mergedRecords != null) {
                assertThat(sheet.mergedRecords, hasSize(7));
                for(MergedRecord record : sheet.mergedRecords) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.headerMergedRecords != null) {
                assertThat(sheet.headerMergedRecords, hasSize(2));
                for(HeaderMergedRecord record : sheet.headerMergedRecords) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.optionalRecords1 != null) {
                assertThat(sheet.optionalRecords1, hasSize(2));
                for(OptionalRecord record : sheet.optionalRecords1) {
                    assertRecord(record, errors, true);
                }
            }
            
            if(sheet.optionalRecords2 != null) {
                assertThat(sheet.optionalRecords2, hasSize(2));
                for(OptionalRecord record : sheet.optionalRecords2) {
                    assertRecord(record, errors, false);
                }
            }
            
            if(sheet.convertedRecord != null) {
                assertThat(sheet.convertedRecord, hasSize(2));
                for(ConvertedRecord record : sheet.convertedRecord) {
                    assertRecord(record, errors, false);
                }
            }
            
        }
    }
    
    /**
     * カラムの設定テスト
     */
    @Test
    public void test_load_vr_mapColumnSetting() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(MapColumnSettingSheet.class);
            
            MapColumnSettingSheet sheet = mapper.load(in, MapColumnSettingSheet.class, errors);
            
            if(sheet.mapRecords1 != null) {
                assertThat(sheet.mapRecords1, hasSize(2));
                for(MapRecord record : sheet.mapRecords1) {
                    assertRecord(record, errors, false);
                }
            }
            
            if(sheet.mapRecords2 != null) {
                assertThat(sheet.mapRecords2, hasSize(2));
                for(MapConvertedRecord record : sheet.mapRecords2) {
                    assertRecord(record, errors, false);
                }
            }
            
            if(sheet.mapRecords3 != null) {
                assertThat(sheet.mapRecords3, hasSize(2));
                for(MapEndRecord record : sheet.mapRecords3) {
                    assertRecord(record, errors, false);
                }
            }
            
        }
    }
    
    /**
     * カラムの設定テスト - バインドエラー
     */
    @Test(expected=TypeBindException.class)
    public void test_load_vr_mapColumnSetting_bind_error() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(false);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(MapColumnSettingSheet.class);
            
            MapColumnSettingSheet sheet = mapper.load(in, MapColumnSettingSheet.class, errors);
            
            fail();
        }
    }
    
    /**
     * 様々なレコード
     * ・空のレコードのスキップ確認
     * ・配列型の確認
     */
    @Test
    public void test_load_vr_recordSetting() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(RecodSettingSheet.class);
            
            RecodSettingSheet sheet = mapper.load(in, RecodSettingSheet.class, errors);
            
            if(sheet.skipList != null) {
                assertThat(sheet.skipList, hasSize(3));
                for(EmptySkipRecord record : sheet.skipList) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.skipSet != null) {
                assertThat(sheet.skipSet, hasSize(3));
                for(EmptySkipRecord record : sheet.skipSet) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.skipArray != null) {
                assertThat(sheet.skipArray, arrayWithSize(3));
                for(EmptySkipRecord record : sheet.skipArray) {
                    assertRecord(record, errors);
                }
            }
            
        }
    }
    
    
    /**
     * 読み込み時のテスト - メソッドに付与したアノテーション
     * @since 1.0
     */
    @Test
    public void test_load_vr_annoMethod() throws Exception {
         XlsMapper mapper = new XlsMapper();
         mapper.getConig().setContinueTypeBindFailure(true);
         
         try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
             SheetBindingErrors errors = new SheetBindingErrors(MethodAnnoSheet.class);
             
             MethodAnnoSheet sheet = mapper.load(in, MethodAnnoSheet.class, errors);
             
             if(sheet.records != null) {
                 assertThat(sheet.records, hasSize(3));
                 for(MethodAnnoRecord record : sheet.records) {
                     assertRecord(record, errors);
                 }
             }
             
             if(sheet.mapRecords != null) {
                 assertThat(sheet.mapRecords, arrayWithSize(2));
                 for(MethodAnnoMapRecord record : sheet.mapRecords) {
                     assertRecord(record, errors, false);
                 }
             }
             
         }
    }
    
    /**
     * 読み込み時のテスト - 表のタイトルの位置のテスト
     * @since 1.0
     */
    @Test
    public void test_load_vr_labelPosition() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(TableLabelSheet.class);
            
            TableLabelSheet sheet = mapper.load(in, TableLabelSheet.class, errors);
            
            if(sheet.leftRecords1 != null) {
                assertThat(sheet.leftRecords1, hasSize(2));
                for(NormalRecord record : sheet.leftRecords1) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.leftRecords2 != null) {
                assertThat(sheet.leftRecords2, hasSize(2));
                for(NormalRecord record : sheet.leftRecords2) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.aboveRecords1 != null) {
                assertThat(sheet.aboveRecords1, hasSize(2));
                for(NormalRecord record : sheet.aboveRecords1) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.aboveRecords2 != null) {
                assertThat(sheet.aboveRecords2, hasSize(2));
                for(NormalRecord record : sheet.aboveRecords2) {
                    assertRecord(record, errors);
                }
            }
            
        }
        
    }
    
    /**
     * データの開始位置の指定のテスト
     * @since 1.1
     */
    @Test
    public void test_load_vr_startedDataPosition() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(false);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(StartedDataPositionSheet.class);
            
            StartedDataPositionSheet sheet = mapper.load(in, StartedDataPositionSheet.class, errors);
            
            if(sheet.distantRecords != null) {
                
                assertThat(sheet.distantRecords, hasSize(2));
                for(StartedDataPositionSheet.DistantRecord record : sheet.distantRecords) {
                    assertRecord(record, errors);
                }
                
            }
            
            if(sheet.headerMergedRecords1 != null) {
                
                assertThat(sheet.headerMergedRecords1, hasSize(2));
                for(StartedDataPositionSheet.HeaderMergedRecord record : sheet.headerMergedRecords1) {
                    assertRecord(record, errors);
                }
                
            }
            
            if(sheet.headerMergedRecords2 != null) {
                
                assertThat(sheet.headerMergedRecords2, hasSize(2));
                for(StartedDataPositionSheet.HeaderMergedRecord record : sheet.headerMergedRecords2) {
                    assertRecord(record, errors);
                }
                
            }
        }
        
    }
    
    /**
     * ラベルを正規表現で指定した場合のテスト
     * @throws Exception
     */
    @Test
    public void test_load_vr_regexLabel() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(false)
            .setNormalizeLabelText(true)
            .setRegexLabelText(true);
        
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(RegexSheet.class);
            
            RegexSheet sheet = mapper.load(in, RegexSheet.class, errors);
            
            if(sheet.records1 != null) {
                
                assertThat(sheet.records1, hasSize(1));
                for(RegexSheet.ResultRecord record : sheet.records1) {
                    assertRecord(record, errors);
                }
                
            }
            
            if(sheet.records2 != null) {
                
                assertThat(sheet.records2, hasSize(1));
                for(RegexSheet.ResultRecord record : sheet.records2) {
                    assertRecord(record, errors);
                }
                
            }
            
            if(sheet.records3 != null) {
                
                assertThat(sheet.records3, hasSize(1));
                for(RegexSheet.ResultRecord record : sheet.records3) {
                    assertRecord(record, errors);
                }
                
            }
            
            if(sheet.records4 != null) {
                
                assertThat(sheet.records4, hasSize(1));
                for(RegexSheet.ResultRecord record : sheet.records4) {
                    assertRecord(record, errors);
                }
                
            }
        }
        
    }
    
    /**
     * ネストした表のテスト
     * @since 1.4
     */
    @Test
    public void test_load_vr_nestedRecords() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        try(InputStream in = new FileInputStream("src/test/data/anno_VerticalRecords.xlsx")) {
            SheetBindingErrors errors = new SheetBindingErrors(NestedSheet.class);
            
            NestedSheet sheet = mapper.load(in, NestedSheet.class, errors);
            
            if(sheet.largeRecords1 != null) {
                sheet.printRecord1();
                
                assertThat(sheet.largeRecords1, hasSize(3));
                for(NestedSheet.LargeRecord record : sheet.largeRecords1) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.largeRecords2 != null) {
                sheet.printRecord2();
                
                assertThat(sheet.largeRecords2, hasSize(3));
                for(NestedSheet.LargeRecord record : sheet.largeRecords2) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.largeRecords3 != null) {
                sheet.printRecord3();
                
                assertThat(sheet.largeRecords3, hasSize(3));
                for(NestedSheet.HeaderMergedLargeRecord record : sheet.largeRecords3) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.oneToOneRecords != null) {
                assertThat(sheet.oneToOneRecords, hasSize(4));
                for(NestedSheet.OneToOneRecord record : sheet.oneToOneRecords) {
                    assertRecord(record, errors);
                }
            }
        }
        
    }
    
    private void assertRecord(final NormalRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.name, is("名前1"));
            assertThat(record.value, is(12.456));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("名前2"));
            assertThat(record.value, is(-12.0));
        }
        
    }
    
    private void assertRecord(final HeaderSpaceSheet.UserRecord record, final SheetBindingErrors errors) {
        
        assertThat(record.labels.get("no"), is("No."));
        assertThat(record.labels.get("name"), is("氏名"));
        assertThat(record.labels.get("tel"), is("電話番号"));
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.tel, is("090-1111-1111"));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.tel, is("090-2222-222"));
            
        }
        
    
    }
    
    private void assertRecord(final MergedRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.category, is(Category.Info));
            assertThat(record.description, is("説明1"));
            
        } else if(record.no == 2) {
            assertThat(record.category, is(Category.Info));
            assertThat(record.description, is("説明2"));
            
        } else if(record.no == 3) {
            assertThat(record.category, is(Category.Warn));
            assertThat(record.description, is("説明2"));
            
        } else if(record.no == 4) {
            assertThat(record.category, is(Category.Warn));
            assertThat(record.description, is("説明2"));
            
        } else if(record.no == 5) {
            assertThat(record.category, is(Category.Error));
            assertThat(record.description, is("説明3"));
            
        } else if(record.no == 6) {
            assertThat(record.category, is(Category.Info));
            assertThat(record.description, is(nullValue()));
            
        } else if(record.no == 7) {
            assertThat(record.category, is(Category.Info));
            assertThat(record.description, is(nullValue()));
            
        }
        
    }
    
    private void assertRecord(final HeaderMergedRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.mail, is("taro.yamada@example.com"));
            assertThat(record.tel, is("0000-1111-2222"));
            assertThat(record.comment, is("コメント1"));
            
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.mail, is("jiro.suzuki@example.com"));
            assertThat(record.tel, is("0000-3333-4444"));
            assertThat(record.comment, is("コメント2"));
        }
        
    }
    
    private void assertRecord(final OptionalRecord record, final SheetBindingErrors errors, boolean hasCell) {
        
        if(record.no == 1) {
            assertThat(record.name, is("名前1"));
            assertThat(record.value, is(12.456));
            
            if(hasCell) {
                assertThat(record.comment, is("コメント1"));
            } else {
                assertThat(record.comment, is(nullValue()));
            }
            
        } else if(record.no == 2) {
            assertThat(record.name, is("名前2"));
            assertThat(record.value, is(-12.0));
            
            if(hasCell) {
                assertThat(record.comment, is("コメント2"));
            } else {
                assertThat(record.comment, is(nullValue()));
            }
        }
        
    }
    
    private void assertRecord(final ConvertedRecord record, final SheetBindingErrors errors, boolean hasCell) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(cellFieldError(errors, cellAddress(record.positions.get("birthday"))).isTypeBindFailure(), is(true));
            assertThat(record.age, is(14));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("1990-02-28 00:00:00.000"))));
            assertThat(cellFieldError(errors, cellAddress(record.positions.get("age"))).isTypeBindFailure(), is(true));
            
        }
        
    }
    
    private void assertRecord(final MapRecord record, final SheetBindingErrors errors, boolean hasCell) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.dateAttended.get("4月1日"), is("出席"));
            assertThat(record.dateAttended.get("4月2日"), is("出席"));
            assertThat(record.dateAttended.get("4月3日"), is(nullValue()));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.dateAttended.get("4月1日"), is("欠席"));
            assertThat(record.dateAttended.get("4月2日"), is("-"));
            assertThat(record.dateAttended.get("4月3日"), is("出席"));
        }
        
    }
    
    private void assertRecord(final MapConvertedRecord record, final SheetBindingErrors errors, boolean hasCell) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.dateAttended.get("4月1日"), is(true));
            assertThat(record.dateAttended.get("4月2日"), is(true));
            assertThat(record.dateAttended.get("4月3日"), is(nullValue()));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.dateAttended.get("4月1日"), is(false));
            assertThat(cellFieldError(errors, cellAddress(record.positions.get("dateAttended[4月2日]"))).isTypeBindFailure(), is(true));
            assertThat(record.dateAttended.get("4月3日"), is(true));
        }
        
    }
    
    private void assertRecord(final MapEndRecord record, final SheetBindingErrors errors, boolean hasCell) {
        
        if(record.no == 1) {
            
            assertThat(record.name, is("山田太郎"));
            assertThat(record.dateAttended.get("4月1日"), is("出席"));
            assertThat(record.dateAttended.get("4月2日"), is("出席"));
            assertThat(record.dateAttended.get("4月3日"), is(nullValue()));
            assertThat(record.dateAttended, not(hasKey("備考")));
            assertThat(record.comment, is("とりあえず出席します。"));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.dateAttended.get("4月1日"), is("欠席"));
            assertThat(record.dateAttended.get("4月2日"), is("-"));
            assertThat(record.dateAttended.get("4月3日"), is("出席"));
            assertThat(record.dateAttended, not(hasKey("備考")));
            assertThat(record.comment, is(nullValue()));
        }
        
    }
    
    private void assertRecord(final EmptySkipRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("1989-01-02 00:00:00.000"))));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("1990-02-28 00:00:00.000"))));
            
        } else if(record.no == 4) {
            assertThat(record.name, is("林三郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("1992-04-14 00:00:00.000"))));
        }
        
    }
    
    private void assertRecord(final MethodAnnoRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("1989-01-02 00:00:00.000"))));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("1990-02-28 00:00:00.000"))));
            
        } else if(record.no == 4) {
            assertThat(record.name, is("林三郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("1992-04-14 00:00:00.000"))));
        }
        
    }
    
    private void assertRecord(final MethodAnnoMapRecord record, final SheetBindingErrors errors, boolean hasCell) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.dateAttended.get("4月1日"), is(true));
            assertThat(record.dateAttended.get("4月2日"), is(true));
            assertThat(record.dateAttended.get("4月3日"), is(nullValue()));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.dateAttended.get("4月1日"), is(false));
            assertThat(cellFieldError(errors, cellAddress(record.dateAttendedPosition.get("4月2日"))).isTypeBindFailure(), is(true));
            assertThat(record.dateAttended.get("4月3日"), is(true));
        }
        
    }
    
    private void assertRecord(final StartedDataPositionSheet.DistantRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.sansu, is(90));
            assertThat(record.kokugo, is(70));
            assertThat(record.sum, is(160));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.sansu, is(80));
            assertThat(record.kokugo, is(90));
            assertThat(record.sum, is(170));
            
        }
        
    }
    
    private void assertRecord(final StartedDataPositionSheet.HeaderMergedRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.sansu, is(90));
            assertThat(record.kokugo, is(70));
            assertThat(record.sum, is(160));
            
        } else if(record.no == 2) {
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.sansu, is(80));
            assertThat(record.kokugo, is(90));
            assertThat(record.sum, is(170));
            
        }
        
    }
    
    private void assertRecord(final RegexSheet.ResultRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.name, is("山田太郎"));
            assertThat(record.resultMap.get("1回目"), is(30));
            assertThat(record.resultMap.get("2回目"), is(40));
            assertThat(record.resultMap.get("3回目"), is(50));
            assertThat(record.resultMap, not(hasKey("備考")));
            assertThat(record.comment, is("改善している。"));
            
        }
        
    }
    
private void assertRecord(final NestedSheet.LargeRecord largeRecord, final SheetBindingErrors errors) {
        
        final String largeName = largeRecord.largeName;
        assertThat(largeRecord.largeDescription, is(String.format("%sの説明", largeName)));
        assertThat(largeRecord.labels.get("largeName"), is("大分類"));
        assertThat(largeRecord.labels.get("largeDescription"), is("説明（大分類）"));
        
        if(largeName.equals("機能A")) {
            assertThat(largeRecord.middleRecords, hasSize(3));
        } else if(largeName.equals("機能B")) {
            assertThat(largeRecord.middleRecords, hasSize(2));
        } else if(largeName.equals("機能C")) {
            assertThat(largeRecord.middleRecords, hasSize(1));
        }
        
        if(largeRecord.middleRecords != null) {
            for(final NestedSheet.MiddleRecord middleRecord : largeRecord.middleRecords) {
                final String middleName = middleRecord.middleName;
                assertThat(middleRecord.middleDescription, is(String.format("%sの説明", middleName)));
                assertThat(middleRecord.labels.get("middleName"), is("中分類"));
                assertThat(middleRecord.labels.get("middleDescription"), is("説明（中分類）"));
                
                if(middleName.equals("機能A1")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(2));
                } else if(middleName.equals("機能A2")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(1));
                } else if(middleName.equals("機能A3")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(3));
                } else if(middleName.equals("機能B1")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(1));
                } else if(middleName.equals("機能B2")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(2));
                } else if(middleName.equals("機能C1")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(1));
                }
                
                for(NestedSheet.SmallRecord smallRecord : middleRecord.smallRecords) {
                    
                    final String smallName = smallRecord.name;
                    assertThat(smallRecord.value, is(String.format("%sの値", smallName)));
                    assertThat(smallRecord.labels.get("name"), is("項目"));
                    assertThat(smallRecord.labels.get("value"), is("値"));
                }
            }
        }
        
        
    }
    
    private void assertRecord(final NestedSheet.HeaderMergedLargeRecord largeRecord, final SheetBindingErrors errors) {
        
        final String largeName = largeRecord.largeName;
        assertThat(largeRecord.largeDescription, is(String.format("%sの説明", largeName)));
        assertThat(largeRecord.labels.get("largeName"), is("大分類"));
        assertThat(largeRecord.labels.get("largeDescription"), is("大分類"));
        
        if(largeName.equals("機能A")) {
            assertThat(largeRecord.middleRecords, hasSize(3));
        } else if(largeName.equals("機能B")) {
            assertThat(largeRecord.middleRecords, hasSize(2));
        } else if(largeName.equals("機能C")) {
            assertThat(largeRecord.middleRecords, hasSize(1));
        }
        
        if(largeRecord.middleRecords != null) {
            for(final NestedSheet.HeaderMergedMiddleRecord middleRecord : largeRecord.middleRecords) {
                final String middleName = middleRecord.middleName;
                assertThat(middleRecord.middleDescription, is(String.format("%sの説明", middleName)));
                assertThat(middleRecord.labels.get("middleName"), is("中分類"));
                assertThat(middleRecord.labels.get("middleDescription"), is("中分類"));
                
                if(middleName.equals("機能A1")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(2));
                } else if(middleName.equals("機能A2")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(1));
                } else if(middleName.equals("機能A3")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(3));
                } else if(middleName.equals("機能B1")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(1));
                } else if(middleName.equals("機能B2")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(2));
                } else if(middleName.equals("機能C1")) {
                    assertThat(middleRecord.smallRecords, arrayWithSize(1));
                }
                
                for(NestedSheet.HeaderMergedSmallRecord smallRecord : middleRecord.smallRecords) {
                    
                    final String smallName = smallRecord.name;
                    assertThat(smallRecord.value, is(String.format("%sの値", smallName)));
                    assertThat(smallRecord.labels.get("name"), is("詳細"));
                    assertThat(smallRecord.labels.get("value"), is("詳細"));
                }
            }
        }
        
        
    }
    
    private void assertRecord(final NestedSheet.OneToOneRecord record, final SheetBindingErrors errors) {
        
        if(record.no == 1) {
            assertThat(record.className, is("A"));
            assertThat(record.name, is("山田太郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("2000-04-01 00:00:00.000"))));
            
            assertThat(record.result.kokugo, is(90));
            assertThat(record.result.sansu, is(70));
            assertThat(record.result.sum, is(160));
            
        } else if(record.no == 2) {
            assertThat(record.className, is("A"));
            assertThat(record.name, is("鈴木次郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("2000-04-02 00:00:00.000"))));
            
            assertThat(record.result.kokugo, is(80));
            assertThat(record.result.sansu, is(90));
            assertThat(record.result.sum, is(170));
            
        } else if(record.no == 3) {
            assertThat(record.className, is("B"));
            assertThat(record.name, is("林三郎"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("2000-05-01 00:00:00.000"))));
            
            assertThat(record.result.kokugo, is(60));
            assertThat(record.result.sansu, is(30));
            assertThat(record.result.sum, is(90));
            
        } else if(record.no == 4) {
            assertThat(record.className, is("B"));
            assertThat(record.name, is("山田花子"));
            assertThat(record.birthday, is(toUtilDate(toTimestamp("2000-05-02 00:00:00.000"))));
            
            assertThat(record.result, is(nullValue()));
        }
        
        
    }
    
    /**
     * 書き込みのテスト - 開始位置の判定
     */
    @Test
    public void test_save_vr_startedPositoin() throws Exception {
        
        // テストデータの作成
        StartedPositionSheet outSheet = new StartedPositionSheet();
        
        outSheet.add1(new NormalRecord().name("1-名前1").value(12.345));
        outSheet.add1(new NormalRecord().name("1-名前2").value(-54.321));
        
        outSheet.add2(new NormalRecord().name("2-名前1").value(12.345));
        outSheet.add2(new NormalRecord().name("2-名前2").value(-54.321));
        
        outSheet.add3(new NormalRecord().name("3-名前1").value(12.345));
        outSheet.add3(new NormalRecord().name("3-名前2").value(-54.321));
        
        outSheet.add4(new NormalRecord().name("4-名前1").value(12.345));
        outSheet.add4(new NormalRecord().name("4-名前2").value(-54.321));
        
        outSheet.add5(new NormalRecord().name("5-名前1").value(12.345));
        outSheet.add5(new NormalRecord().name("5-名前2").value(-54.321));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(StartedPositionSheet.class);
            
            StartedPositionSheet sheet = mapper.load(in, StartedPositionSheet.class, errors);
            
            if(sheet.normalRecords1 != null) {
                assertThat(sheet.normalRecords1, hasSize(outSheet.normalRecords1.size()));
                
                for(int i=0; i < sheet.normalRecords1.size(); i++) {
                    assertRecord(sheet.normalRecords1.get(i), outSheet.normalRecords1.get(i), errors);
                }
                
            }
            
            if(sheet.normalRecords2 != null) {
                assertThat(sheet.normalRecords2, hasSize(outSheet.normalRecords2.size()));
                
                for(int i=0; i < sheet.normalRecords2.size(); i++) {
                    assertRecord(sheet.normalRecords2.get(i), outSheet.normalRecords2.get(i), errors);
                }
                
            }
            
            if(sheet.normalRecords3 != null) {
                assertThat(sheet.normalRecords3, hasSize(outSheet.normalRecords3.size()));
                
                for(int i=0; i < sheet.normalRecords3.size(); i++) {
                    assertRecord(sheet.normalRecords3.get(i), outSheet.normalRecords3.get(i), errors);
                }
                
            }
            
            if(sheet.normalRecords4 != null) {
                assertThat(sheet.normalRecords4, hasSize(outSheet.normalRecords4.size()));
                
                for(int i=0; i < sheet.normalRecords4.size(); i++) {
                    assertRecord(sheet.normalRecords4.get(i), outSheet.normalRecords4.get(i), errors);
                }
                
            }
            
            assertThat(sheet.normalRecords5, is(nullValue()));
            
        }
        
    }
    
    /**
     * 書き込むのテスト -  開始位置の指定のテスト - ラベル指定が不正
     * @throws Exception
     */
    @Test(expected=CellNotFoundException.class)
    public void test_save_vr_startedPosition_errors1() throws Exception {
        
        // テストデータの作成
        StartedPositionError1Sheet outSheet = new StartedPositionError1Sheet();
        
        outSheet.add(new NormalRecord().name("名前1").value(12.345));
        outSheet.add(new NormalRecord().name("名前2").value(-54.321));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
            
            fail();
        }
        
    }
    
    /**
     * 書き込むのテスト -  開始位置の指定のテスト - アドレス指定の書式が不正
     * @throws Exception
     */
    @Test(expected=AnnotationInvalidException.class)
    public void test_save_vr_startedPosition_errors2() throws Exception {
        
        // テストデータの作成
        StartedPositionError2Sheet outSheet = new StartedPositionError2Sheet();
        
        outSheet.add(new NormalRecord().name("名前1").value(12.345));
        outSheet.add(new NormalRecord().name("名前2").value(-54.321));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
            
            fail();
        }
        
    }
    
    /**
     * 書き込むのテスト -  開始位置の指定のテスト - インデックス指定の書式が不正
     * @throws Exception
     */
    @Test(expected=AnnotationInvalidException.class)
    public void test_save_vr_startedPosition_errors3() throws Exception {
        
        // テストデータの作成
        StartedPositionError3Sheet outSheet = new StartedPositionError3Sheet();
        
        outSheet.add(new NormalRecord().name("名前1").value(12.345));
        outSheet.add(new NormalRecord().name("名前2").value(-54.321));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
            
            fail();
        }
        
    }
    
    /**
     * 書き込みのテスト - 終了位置の指定の確認
     */
    @Test
    public void test_save_vr_endPosition() throws Exception {
        
        // テストデータの作成
        EndPositionSheet outSheet = new EndPositionSheet();
        
        outSheet.add1(new NormalRecord().name("1-名前1").value(12.345));
        outSheet.add1(new NormalRecord().name("1-名前2").value(-54.321));
        
        outSheet.add2(new NormalRecord().name("2-名前1").value(12.345));
        outSheet.add2(new NormalRecord().name("2-名前2").value(-54.321));
        
        outSheet.add3(new NormalRecord().name("3-名前1").value(12.345));
        outSheet.add3(new NormalRecord().name("3-名前2").value(-54.321));
        
        outSheet.add4(new NormalRecord().name("4-名前1").value(12.345));
        outSheet.add4(new NormalRecord().name("4-名前2").value(-54.321));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(EndPositionSheet.class);
            
            EndPositionSheet sheet = mapper.load(in, EndPositionSheet.class, errors);
            
            if(sheet.normalRecords1 != null) {
                assertThat(sheet.normalRecords1, hasSize(outSheet.normalRecords1.size()));
                
                for(int i=0; i < sheet.normalRecords1.size(); i++) {
                    assertRecord(sheet.normalRecords1.get(i), outSheet.normalRecords1.get(i), errors);
                }
                
            }
            
            if(sheet.normalRecords2 != null) {
                assertThat(sheet.normalRecords2, hasSize(outSheet.normalRecords2.size()));
                
                for(int i=0; i < sheet.normalRecords2.size(); i++) {
                    assertRecord(sheet.normalRecords2.get(i), outSheet.normalRecords2.get(i), errors);
                }
                
            }
            
            if(sheet.normalRecords3 != null) {
                assertThat(sheet.normalRecords3, hasSize(outSheet.normalRecords3.size()));
                
                for(int i=0; i < sheet.normalRecords3.size(); i++) {
                    assertRecord(sheet.normalRecords3.get(i), outSheet.normalRecords3.get(i), errors);
                }
                
            }
            
            if(sheet.normalRecords4 != null) {
                assertThat(sheet.normalRecords4, hasSize(outSheet.normalRecords4.size()));
                
                for(int i=0; i < sheet.normalRecords4.size(); i++) {
                    assertRecord(sheet.normalRecords4.get(i), outSheet.normalRecords4.get(i), errors);
                }
                
            }
            
        }
    }
    
    /**
     * 書き込みのテスト - 見出しの空白がある場合のテスト
     */
    @Test
    public void test_save_vr_headerSpace() throws Exception {
        
        // テストデータの作成
        HeaderSpaceSheet outSheet = new HeaderSpaceSheet();
        
        outSheet.addRecord1(new HeaderSpaceSheet.UserRecord().name("山田太郎").tel("090-1111-1111"));
        outSheet.addRecord1(new HeaderSpaceSheet.UserRecord().name("鈴木次郎").tel("090-2222-222"));
        
        outSheet.addRecord2(new HeaderSpaceSheet.UserRecord().name("山田太郎").tel("090-1111-1111"));
        outSheet.addRecord2(new HeaderSpaceSheet.UserRecord().name("鈴木次郎").tel("090-2222-222"));
        
        outSheet.addRecord3(new HeaderSpaceSheet.UserRecord().name("山田太郎").tel("090-1111-1111"));
        outSheet.addRecord3(new HeaderSpaceSheet.UserRecord().name("鈴木次郎").tel("090-2222-222"));
        
        outSheet.addRecord4(new HeaderSpaceSheet.UserRecord().name("山田太郎").tel("090-1111-1111"));
        outSheet.addRecord4(new HeaderSpaceSheet.UserRecord().name("鈴木次郎").tel("090-2222-222"));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(HeaderSpaceSheet.class);
            
            HeaderSpaceSheet sheet = mapper.load(in, HeaderSpaceSheet.class, errors);
            
            if(sheet.records1 != null) {
                assertThat(sheet.records1, hasSize(outSheet.records1.size()));
                
                for(int i=0; i < sheet.records1.size(); i++) {
                    assertRecord(sheet.records1.get(i), outSheet.records1.get(i), errors);
                }
                
            }
            
            if(sheet.records2 != null) {
                assertThat(sheet.records2, hasSize(outSheet.records2.size()));
                
                for(int i=0; i < sheet.records2.size(); i++) {
                    assertRecord(sheet.records2.get(i), outSheet.records2.get(i), errors);
                }
                
            }
            
            if(sheet.records3 != null) {
                assertThat(sheet.records3, hasSize(outSheet.records3.size()));
                
                for(int i=0; i < sheet.records3.size(); i++) {
                    assertRecord(sheet.records3.get(i), outSheet.records3.get(i), errors);
                }
                
            }
            
            if(sheet.records4 != null) {
                assertThat(sheet.records4, hasSize(outSheet.records4.size()));
                
                for(int i=0; i < sheet.records4.size(); i++) {
                    assertRecord(sheet.records4.get(i), outSheet.records4.get(i), errors);
                }
                
            }
            
        }
        
    }
    
    /**
     * 書き込みのテスト - カラム設定のテスト
     * @throws Exception
     */
    @Test
    public void test_save_vr_columnSetting1() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        test_save_vr_columnSetting(mapper);
    }
    
    /**
     * 書き込みのテスト - カラム設定のテスト
     * - 結合を有効
     * @throws Exception
     */
    @Test
    public void test_save_vr_columnSetting2() throws Exception {
        
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true)
            .setMergeCellOnSave(true);
        
        test_save_vr_columnSetting(mapper);
    }
    
    /**
     * 書き込みのテスト - カラム設定のテスト
     */
    private void test_save_vr_columnSetting(final XlsMapper mapper) throws Exception {
        
        // テストデータの作成
        final ColumnSettingSheet outSheet = new ColumnSettingSheet();
        
        // 結合セルの作成
        outSheet.addMerged(new MergedRecord()
                .category(Category.Info).description("説明1"));
        
        outSheet.addMerged(new MergedRecord()
                .category(Category.Info).description("説明2"));
        
        outSheet.addMerged(new MergedRecord()
                .category(Category.Warn).description("説明2"));
        
        outSheet.addMerged(new MergedRecord()
                .category(Category.Warn).description("説明2"));
        
        outSheet.addMerged(new MergedRecord()
                .category(Category.Error).description("説明3"));
        
        outSheet.addMerged(new MergedRecord()
                .category(Category.Info));
        
        outSheet.addMerged(new MergedRecord()
                .category(Category.Info));
        
        // 見出しが結合
        outSheet.addHeaderMerged(new HeaderMergedRecord()
                .name("山田太郎").mail("taro.yamada@example.com").tel("0000-1111-2222").comment("コメント1"));
        
        outSheet.addHeaderMerged(new HeaderMergedRecord()
                .name("鈴木次郎").mail("jiro.suzuki@example.com").tel("0000-3333-4444").comment("コメント1"));
        
        // オプションのセル（セルがある）
        outSheet.addOptional1(new OptionalRecord()
                .name("名前1").value(12.345).comment("コメント1"));
        outSheet.addOptional1(new OptionalRecord()
                .name("名前2").value(-12.0).comment("コメント2"));
        
        // オプションのセル（セルがない）
        outSheet.addOptional2(new OptionalRecord()
                .name("名前1").value(12.345).comment("コメント1"));
        outSheet.addOptional2(new OptionalRecord()
                .name("名前2").value(-12.0).comment("コメント2"));
        
        // Converterがある
        outSheet.addConverted(new ConvertedRecord()
                .name("山田太郎").birthday(toUtilDate(toTimestamp("1981-02-01 00:00:00.000"))).age(14));
        
        outSheet.addConverted(new ConvertedRecord()
                .name("  鈴木次郎  ").birthday(toUtilDate(toTimestamp("1990-02-28 00:00:00.000"))));
        
        // ファイルへの書き込み
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(ColumnSettingSheet.class);
            
            ColumnSettingSheet sheet = mapper.load(in, ColumnSettingSheet.class, errors);
            
            if(sheet.mergedRecords != null) {
                assertThat(sheet.mergedRecords, hasSize(outSheet.mergedRecords.size()));
                
                for(int i=0; i < sheet.mergedRecords.size(); i++) {
                    assertRecord(sheet.mergedRecords.get(i), outSheet.mergedRecords.get(i), errors);
                }
                
            }
            
            if(sheet.headerMergedRecords != null) {
                assertThat(sheet.headerMergedRecords, hasSize(outSheet.headerMergedRecords.size()));
                
                for(int i=0; i < sheet.headerMergedRecords.size(); i++) {
                    assertRecord(sheet.headerMergedRecords.get(i), outSheet.headerMergedRecords.get(i), errors);
                }
                
            }
            
            if(sheet.optionalRecords1 != null) {
                assertThat(sheet.optionalRecords1, hasSize(outSheet.optionalRecords1.size()));
                
                for(int i=0; i < sheet.optionalRecords1.size(); i++) {
                    assertRecord(sheet.optionalRecords1.get(i), outSheet.optionalRecords1.get(i), errors, true);
                }
                
            }
            
            if(sheet.optionalRecords2 != null) {
                assertThat(sheet.optionalRecords2, hasSize(outSheet.optionalRecords2.size()));
                
                for(int i=0; i < sheet.optionalRecords2.size(); i++) {
                    assertRecord(sheet.optionalRecords2.get(i), outSheet.optionalRecords2.get(i), errors, false);
                }
                
            }
            
            if(sheet.convertedRecord != null) {
                assertThat(sheet.convertedRecord, hasSize(outSheet.convertedRecord.size()));
                
                for(int i=0; i < sheet.convertedRecord.size(); i++) {
                    assertRecord(sheet.convertedRecord.get(i), outSheet.convertedRecord.get(i), errors);
                }
                
            }
            
        }
        
    }
    
    /**
     * 書き込みテスト - Map形式のカラムの設定テスト
     */
    @Test
    public void test_save_vr_mapColumnSetting() throws Exception {
        
        // テストデータの作成
        final MapColumnSettingSheet outSheet = new MapColumnSettingSheet();
        
        // マップカラム（文字列）
        outSheet.add(new MapRecord()
                    .name("山田太郎")
                    .addDateAttended("4月1日", "出席").addDateAttended("4月2日", "出席"));
        
        outSheet.add(new MapRecord()
                .name("鈴木次郎")
                .addDateAttended("4月1日", "欠席").addDateAttended("4月2日", "-").addDateAttended("4月3日", "出席"));
        
        // マップカラム（Converterあり）
        outSheet.add(new MapConvertedRecord()
                    .name("山田太郎")
                    .addDateAttended("4月1日", true).addDateAttended("4月2日", true));
        
        outSheet.add(new MapConvertedRecord()
                .name("鈴木次郎")
                .addDateAttended("4月1日", false).addDateAttended("4月2日", false).addDateAttended("4月3日", true));
        
        // マップカラム（終了条件あり）
        outSheet.add(new MapEndRecord()
                .name("山田太郎")
                .addDateAttended("4月1日", "出席").addDateAttended("4月2日", "出席").comment("とりあえず出席します。"));
        
        outSheet.add(new MapEndRecord()
                .name("鈴木次郎")
                .addDateAttended("4月1日", "欠席").addDateAttended("4月2日", "-").addDateAttended("4月3日", "出席"));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(MapColumnSettingSheet.class);
            
            MapColumnSettingSheet sheet = mapper.load(in, MapColumnSettingSheet.class, errors);
            
            if(sheet.mapRecords1 != null) {
                assertThat(sheet.mapRecords1, hasSize(outSheet.mapRecords1.size()));
                
                for(int i=0; i < sheet.mapRecords1.size(); i++) {
                    assertRecord(sheet.mapRecords1.get(i), outSheet.mapRecords1.get(i), errors);
                }
                
            }
            
            if(sheet.mapRecords2 != null) {
                assertThat(sheet.mapRecords2, hasSize(outSheet.mapRecords2.size()));
                
                for(int i=0; i < sheet.mapRecords2.size(); i++) {
                    assertRecord(sheet.mapRecords2.get(i), outSheet.mapRecords2.get(i), errors);
                }
                
            }
            
            if(sheet.mapRecords3 != null) {
                assertThat(sheet.mapRecords3, hasSize(outSheet.mapRecords3.size()));
                
                for(int i=0; i < sheet.mapRecords3.size(); i++) {
                    assertRecord(sheet.mapRecords3.get(i), outSheet.mapRecords3.get(i), errors);
                }
                
            }
            
        }
    }
    
    /**
     * 書き込みのテスト - 様々なレコード
     * ・空のレコードのスキップ確認
     * ・配列型の確認
     */
    @Test
    public void test_save_vr_recordSetting() throws Exception {
        
        // テストデータの作成
        final RecodSettingSheet outSheet = new RecodSettingSheet();
        
        // 名簿（リスト）
        outSheet.addList(new EmptySkipRecord().name("山田太郎").birthday(toUtilDate(toTimestamp("1989-01-02 00:00:00.000"))));
        outSheet.addList(new EmptySkipRecord().name("鈴木次郎").birthday(toUtilDate(toTimestamp("1990-02-28 00:00:00.000"))));
        outSheet.addList(new EmptySkipRecord());
        outSheet.addList(new EmptySkipRecord().name("林三郎").birthday(toUtilDate(toTimestamp("1992-04-14 00:00:00.000"))));
        outSheet.addList(new EmptySkipRecord());
        
        // 名簿（集合）
        outSheet.addSet(new EmptySkipRecord().name("山田太郎").birthday(toUtilDate(toTimestamp("1989-01-02 00:00:00.000"))));
        outSheet.addSet(new EmptySkipRecord().name("鈴木次郎").birthday(toUtilDate(toTimestamp("1990-02-28 00:00:00.000"))));
        outSheet.addSet(new EmptySkipRecord());
        outSheet.addSet(new EmptySkipRecord().name("林三郎").birthday(toUtilDate(toTimestamp("1992-04-14 00:00:00.000"))));
        outSheet.addSet(new EmptySkipRecord());
        
        // 名簿（配列）
        outSheet.addArray(new EmptySkipRecord().name("山田太郎").birthday(toUtilDate(toTimestamp("1989-01-02 00:00:00.000"))));
        outSheet.addArray(new EmptySkipRecord().name("鈴木次郎").birthday(toUtilDate(toTimestamp("1990-02-28 00:00:00.000"))));
        outSheet.addArray(new EmptySkipRecord());
        outSheet.addArray(new EmptySkipRecord().name("林三郎").birthday(toUtilDate(toTimestamp("1992-04-14 00:00:00.000"))));
        outSheet.addArray(new EmptySkipRecord());
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(RecodSettingSheet.class);
            
            RecodSettingSheet sheet = mapper.load(in, RecodSettingSheet.class, errors);
            
            if(sheet.skipList != null) {
                int emptyRecordCount = 0;
                for(int i=0; i < outSheet.skipList.size(); i++) {
                    if(outSheet.skipList.get(i).isEmpty()) {
                        emptyRecordCount++;
                        continue;
                    }
                    assertRecord(sheet.skipList.get(i - emptyRecordCount), outSheet.skipList.get(i), errors);
                }
                
                assertThat(sheet.skipList, hasSize(outSheet.skipList.size() - emptyRecordCount));
                
            }
            
            if(sheet.skipSet != null) {
                int emptyRecordCount = 0;
                for(int i=0; i < outSheet.skipSet.size(); i++) {
                    if(outSheet.skipSet.toArray(new EmptySkipRecord[0])[i].isEmpty()) {
                        emptyRecordCount++;
                        continue;
                    }
                    assertRecord(sheet.skipSet.toArray(new EmptySkipRecord[0])[i - emptyRecordCount], outSheet.skipSet.toArray(new EmptySkipRecord[0])[i], errors);
                }
                
                assertThat(sheet.skipList, hasSize(outSheet.skipList.size() - emptyRecordCount));
                
            }
            
            if(sheet.skipArray != null) {
                int emptyRecordCount = 0;
                for(int i=0; i < outSheet.skipArray.length; i++) {
                    if(outSheet.skipArray[i].isEmpty()) {
                        emptyRecordCount++;
                        continue;
                    }
                    assertRecord(sheet.skipArray[i - emptyRecordCount], outSheet.skipArray[i], errors);
                }
                
                assertThat(sheet.skipArray, arrayWithSize(outSheet.skipArray.length - emptyRecordCount));
                
            }
            
        }
    }
    
    /**
     * 書き込みのテスト - 余分なレコード／足りないレコードの制御
     */
    @Test
    public void test_save_vr_over_remained_record() throws Exception {
        
        // テストデータの作成
        final RemainedOverSheet outSheet = new RemainedOverSheet();
        
        // 足りないレコード（Break）
        outSheet.addOverBreak(new RemainedOverRecord().name("山田太郎").addDateAttended("A", "○").addDateAttended("B", "×"));
        outSheet.addOverBreak(new RemainedOverRecord().name("鈴木次郎").addDateAttended("A", "-").addDateAttended("B", "-"));
        outSheet.addOverBreak(new RemainedOverRecord().name("山本花子").addDateAttended("A", "×").addDateAttended("B", "レ"));
        
        // 足りないレコード（Inert）
        outSheet.addOverInsert(new RemainedOverRecord().name("山田太郎").addDateAttended("A", "○").addDateAttended("B", "×"));
        outSheet.addOverInsert(new RemainedOverRecord().name("鈴木次郎").addDateAttended("A", "-").addDateAttended("B", "-"));
        outSheet.addOverInsert(new RemainedOverRecord().name("山本花子").addDateAttended("A", "×").addDateAttended("B", "レ"));
        
        // 足りないレコード（Copy）
        outSheet.addOverCopy(new RemainedOverRecord().name("山田太郎").addDateAttended("A", "○").addDateAttended("B", "×"));
        outSheet.addOverCopy(new RemainedOverRecord().name("鈴木次郎").addDateAttended("A", "-").addDateAttended("B", "-"));
        outSheet.addOverCopy(new RemainedOverRecord().name("山本花子").addDateAttended("A", "×").addDateAttended("B", "レ"));
        
        // 余分なレコード（None）
        outSheet.addRemainedNone(new RemainedOverRecord().name("山田太郎").addDateAttended("A", "○").addDateAttended("B", "×"));
        outSheet.addRemainedNone(new RemainedOverRecord().name("鈴木次郎").addDateAttended("A", "-").addDateAttended("B", "-"));
        
        // 余分なレコード（Clear）
        outSheet.addRemainedClear(new RemainedOverRecord().name("山田太郎").addDateAttended("A", "○").addDateAttended("B", "×"));
        outSheet.addRemainedClear(new RemainedOverRecord().name("鈴木次郎").addDateAttended("A", "-").addDateAttended("B", "-"));
        
        // 余分なレコード（Delete）
        outSheet.addRemainedDelete1(new RemainedOverRecord().name("山田太郎").addDateAttended("A", "○").addDateAttended("B", "×"));
        outSheet.addRemainedDelete1(new RemainedOverRecord().name("鈴木次郎").addDateAttended("A", "-").addDateAttended("B", "-"));
        
        // 余分なレコード（Delete）(データなし)
        outSheet.remainedDeleteRecrods2 = new ArrayList<>();
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true)
            .setCorrectCellDataValidationOnSave(true)
            .setCorrectNameRangeOnSave(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(RemainedOverSheet.class);
            
            RemainedOverSheet sheet = mapper.load(in, RemainedOverSheet.class, errors);
            
            if(sheet.overBreakRecrods != null) {
                assertThat(sheet.overBreakRecrods, hasSize(2));
                
                for(int i=0; i < sheet.overBreakRecrods.size(); i++) {
                    assertRecord(sheet.overBreakRecrods.get(i), outSheet.overBreakRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.overInsertRecrods != null) {
                assertThat(sheet.overInsertRecrods, hasSize(outSheet.overInsertRecrods.size()));
                
                for(int i=0; i < sheet.overInsertRecrods.size(); i++) {
                    assertRecord(sheet.overInsertRecrods.get(i), outSheet.overInsertRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.overCopyRecrods != null) {
                assertThat(sheet.overCopyRecrods, hasSize(outSheet.overCopyRecrods.size()));
                
                for(int i=0; i < sheet.overCopyRecrods.size(); i++) {
                    assertRecord(sheet.overCopyRecrods.get(i), outSheet.overCopyRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.remainedNoneRecrods != null) {
                assertThat(sheet.remainedNoneRecrods, hasSize(outSheet.remainedNoneRecrods.size()));
                
                for(int i=0; i < sheet.remainedNoneRecrods.size(); i++) {
                    assertRecord(sheet.remainedNoneRecrods.get(i), outSheet.remainedNoneRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.remainedClearRecrods != null) {
                assertThat(sheet.remainedClearRecrods, hasSize(outSheet.remainedClearRecrods.size()));
                
                for(int i=0; i < sheet.remainedClearRecrods.size(); i++) {
                    assertRecord(sheet.remainedClearRecrods.get(i), outSheet.remainedClearRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.remainedDeleteRecrods1 != null) {
                assertThat(sheet.remainedDeleteRecrods1, hasSize(outSheet.remainedDeleteRecrods1.size()));
                
                for(int i=0; i < sheet.remainedDeleteRecrods1.size(); i++) {
                    assertRecord(sheet.remainedDeleteRecrods1.get(i), outSheet.remainedDeleteRecrods1.get(i), errors);
                }
                
            }
            
            if(sheet.remainedDeleteRecrods2 != null) {
                assertThat(sheet.remainedDeleteRecrods2, hasSize(outSheet.remainedDeleteRecrods2.size()));
                
                for(int i=0; i < sheet.remainedDeleteRecrods2.size(); i++) {
                    assertRecord(sheet.remainedDeleteRecrods2.get(i), outSheet.remainedDeleteRecrods2.get(i), errors);
                }
                
            }
            
        }
    }
    
    /**
     * 書き込みのテスト - 入力規則
     */
    @Test
    public void test_save_vr_option_dataValidation() throws Exception {
        
        // テストデータの作成
        final ValidationRuleSheet outSheet = new ValidationRuleSheet();
        
        // 入力規則（レコードの挿入）
        outSheet.addInsert(new DataValidationRecord().selectRule(true).refRule("ユーザ管理").addCategory("A", true).addCategory("B", false));
        outSheet.addInsert(new DataValidationRecord().selectRule(false).refRule("ファイルアップロード").addCategory("A", null).addCategory("B", true));
        outSheet.addInsert(new DataValidationRecord());
        
        // 名前の定義
        outSheet.add(new NameDefRecord().functionName("ユーザ管理"));
        outSheet.add(new NameDefRecord().functionName("ファイルアップロード"));
        outSheet.add(new NameDefRecord().functionName("データ管理"));
        outSheet.add(new NameDefRecord().functionName("帳票出力"));
        outSheet.add(new NameDefRecord());
        
        // 入力規則（レコードの削除）
        outSheet.addDelete(new DataValidationRecord().selectRule(true).refRule("作成").addCategory("A", true).addCategory("B", true));
        outSheet.addDelete(new DataValidationRecord().selectRule(false).refRule("削除").addCategory("A", null).addCategory("B", false));
        outSheet.addDelete(new DataValidationRecord());
        
        // 入力規則（レコードの削除）（データなし）
        outSheet.nonDeleteValidationRecrods = new ArrayList<>();
        
        // 入力規則（レコードのコピー）
        outSheet.addCopy(new DataValidationRecord().selectRule(true).refRule("参照").addCategory("A", null).addCategory("B", null));
        outSheet.addCopy(new DataValidationRecord().selectRule(false).refRule("更新").addCategory("A", true).addCategory("B", true));
        outSheet.addCopy(new DataValidationRecord());
        
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true)
            .setCorrectCellDataValidationOnSave(true)
            .setCorrectNameRangeOnSave(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(ValidationRuleSheet.class);
            
            ValidationRuleSheet sheet = mapper.load(in, ValidationRuleSheet.class, errors);
            
            if(sheet.insertValidationRecrods != null) {
                assertThat(sheet.insertValidationRecrods, hasSize(outSheet.insertValidationRecrods.size()-1));
                
                for(int i=0; i < sheet.insertValidationRecrods.size(); i++) {
                    assertRecord(sheet.insertValidationRecrods.get(i), outSheet.insertValidationRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.nameRecords != null) {
                assertThat(sheet.nameRecords, hasSize(outSheet.nameRecords.size()));
                
                for(int i=0; i < sheet.nameRecords.size(); i++) {
                    assertRecord(sheet.nameRecords.get(i), outSheet.nameRecords.get(i), errors);
                }
                
            }
            
            if(sheet.deleteValidationRecrods != null) {
                assertThat(sheet.deleteValidationRecrods, hasSize(1));
                
                for(int i=0; i < sheet.deleteValidationRecrods.size(); i++) {
                    assertRecord(sheet.deleteValidationRecrods.get(i), outSheet.deleteValidationRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.nonDeleteValidationRecrods != null) {
                assertThat(sheet.nonDeleteValidationRecrods, hasSize(outSheet.nonDeleteValidationRecrods.size()));
                
                for(int i=0; i < sheet.nonDeleteValidationRecrods.size(); i++) {
                    assertRecord(sheet.nonDeleteValidationRecrods.get(i), outSheet.nonDeleteValidationRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.copyValidationRecrods != null) {
                assertThat(sheet.copyValidationRecrods, hasSize(outSheet.copyValidationRecrods.size()-1));
                
                for(int i=0; i < sheet.copyValidationRecrods.size(); i++) {
                    assertRecord(sheet.copyValidationRecrods.get(i), outSheet.copyValidationRecrods.get(i), errors);
                }
                
            }
            
        }
        
    }
    
    /**
     * 書き込みのテスト - メソッドにアノテーションを定義
     * @since 1.0
     */
    @Test
    public void test_save_vr_methodAnno() throws Exception {
        
        // テストデータの作成
        final MethodAnnoSheet outSheet = new MethodAnnoSheet();
        
        // 名簿
        outSheet.add(new MethodAnnoRecord().name("山田太郎").birthday(toUtilDate(toTimestamp("1989-01-02 00:00:00.000"))));
        outSheet.add(new MethodAnnoRecord().name("鈴木次郎").birthday(toUtilDate(toTimestamp("1990-02-28 00:00:00.000"))));
        outSheet.add(new MethodAnnoRecord());
        outSheet.add(new MethodAnnoRecord().name("林三郎").birthday(toUtilDate(toTimestamp("1992-04-14 00:00:00.000"))));
        outSheet.add(new MethodAnnoRecord());
        
        // 出欠
        outSheet.add(new MethodAnnoMapRecord()
                .name("山田太郎")
                .addDateAttended("4月1日", true).addDateAttended("4月2日", true));
        
        outSheet.add(new MethodAnnoMapRecord()
                .name("鈴木次郎")
                .addDateAttended("4月1日", false).addDateAttended("4月2日", false).addDateAttended("4月3日", true));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(MethodAnnoSheet.class);
            
            MethodAnnoSheet sheet = mapper.load(in, MethodAnnoSheet.class, errors);
            
            if(sheet.records != null) {
                int emptyRecordCount = 0;
                for(int i=0; i < outSheet.records.size(); i++) {
                    if(outSheet.records.get(i).isEmpty()) {
                        emptyRecordCount++;
                        continue;
                    }
                    assertRecord(sheet.records.get(i - emptyRecordCount), outSheet.records.get(i), errors);
                }
                
                assertThat(sheet.records, hasSize(outSheet.records.size() - emptyRecordCount));
                
            }
            
            if(sheet.mapRecords != null) {
                int emptyRecordCount = 0;
                for(int i=0; i < outSheet.mapRecords.length; i++) {
                    if(outSheet.mapRecords[i].isEmpty()) {
                        emptyRecordCount++;
                        continue;
                    }
                    assertRecord(sheet.mapRecords[i - emptyRecordCount], outSheet.mapRecords[i], errors);
                }
                
                assertThat(sheet.mapRecords, arrayWithSize(outSheet.mapRecords.length - emptyRecordCount));
                
            }
            
        }
    }
    
    /**
     * 書き込みのテスト - 表のタイトルの位置
     */
    @Test
    public void test_save_vr_labelPosition() throws Exception {
        
        // テストデータの作成
        TableLabelSheet outSheet = new TableLabelSheet();
        
        outSheet.addLeft1(new NormalRecord().name("1-名前1").value(12.345));
        outSheet.addLeft1(new NormalRecord().name("1-名前2").value(-54.321));
        
        outSheet.addLeft2(new NormalRecord().name("2-名前1").value(12.345));
        outSheet.addLeft2(new NormalRecord().name("2-名前2").value(-54.321));
        
        outSheet.addRight1(new NormalRecord().name("3-名前1").value(12.345));
        outSheet.addRight1(new NormalRecord().name("3-名前2").value(-54.321));
        
        outSheet.addRight2(new NormalRecord().name("4-名前1").value(12.345));
        outSheet.addRight2(new NormalRecord().name("4-名前2").value(-54.321));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(TableLabelSheet.class);
            
            TableLabelSheet sheet = mapper.load(in, TableLabelSheet.class, errors);
            
            if(sheet.leftRecords1 != null) {
                assertThat(sheet.leftRecords1, hasSize(outSheet.leftRecords1.size()));
                
                for(int i=0; i < sheet.leftRecords1.size(); i++) {
                    assertRecord(sheet.leftRecords1.get(i), outSheet.leftRecords1.get(i), errors);
                }
                
            }
            
            if(sheet.leftRecords2 != null) {
                assertThat(sheet.leftRecords2, hasSize(outSheet.leftRecords2.size()));
                
                for(int i=0; i < sheet.leftRecords2.size(); i++) {
                    assertRecord(sheet.leftRecords2.get(i), outSheet.leftRecords2.get(i), errors);
                }
                
            }
            
            if(sheet.aboveRecords1 != null) {
                assertThat(sheet.aboveRecords1, hasSize(outSheet.aboveRecords1.size()));
                
                for(int i=0; i < sheet.aboveRecords1.size(); i++) {
                    assertRecord(sheet.aboveRecords1.get(i), outSheet.aboveRecords1.get(i), errors);
                }
                
            }
            
            if(sheet.aboveRecords2 != null) {
                assertThat(sheet.aboveRecords2, hasSize(outSheet.aboveRecords2.size()));
                
                for(int i=0; i < sheet.aboveRecords2.size(); i++) {
                    assertRecord(sheet.aboveRecords2.get(i), outSheet.aboveRecords2.get(i), errors);
                }
                
            }
            
        }
        
    }
    
    /**
     * 書き込みのテスト - データ行の開始位置の判定
     * @since 1.1
     */
    @Test
    public void test_save_vr_startedDataPositoin() throws Exception {
        // テストデータの作成
        StartedDataPositionSheet outSheet = new StartedDataPositionSheet();
        
        outSheet.addDistantRecord(new StartedDataPositionSheet.DistantRecord().name("山田太郎").sansu(90).kokugo(70).sum(160));
        outSheet.addDistantRecord(new StartedDataPositionSheet.DistantRecord().name("鈴木次郎").sansu(80).kokugo(90).sum(170));
        
        outSheet.addHeaderMergedRecord1(new StartedDataPositionSheet.HeaderMergedRecord().name("山田太郎").sansu(90).kokugo(70).sum(160));
        outSheet.addHeaderMergedRecord1(new StartedDataPositionSheet.HeaderMergedRecord().name("鈴木次郎").sansu(80).kokugo(90).sum(170));
        
        outSheet.addHeaderMergedRecord2(new StartedDataPositionSheet.HeaderMergedRecord().name("山田太郎").sansu(90).kokugo(70).sum(160));
        outSheet.addHeaderMergedRecord2(new StartedDataPositionSheet.HeaderMergedRecord().name("鈴木次郎").sansu(80).kokugo(90).sum(170));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(StartedDataPositionSheet.class);
            
            StartedDataPositionSheet sheet = mapper.load(in, StartedDataPositionSheet.class, errors);
            
            if(sheet.distantRecords != null) {
                assertThat(sheet.distantRecords, hasSize(outSheet.distantRecords.size()));
                
                for(int i=0; i < sheet.distantRecords.size(); i++) {
                    assertRecord(sheet.distantRecords.get(i), outSheet.distantRecords.get(i), errors);
                }
                
            }
            
            if(sheet.headerMergedRecords1 != null) {
                assertThat(sheet.headerMergedRecords1, hasSize(outSheet.headerMergedRecords1.size()));
                
                for(int i=0; i < sheet.headerMergedRecords1.size(); i++) {
                    assertRecord(sheet.headerMergedRecords1.get(i), outSheet.headerMergedRecords1.get(i), errors);
                }
                
            }
            
            if(sheet.headerMergedRecords2 != null) {
                assertThat(sheet.headerMergedRecords2, hasSize(outSheet.headerMergedRecords2.size()));
                
                for(int i=0; i < sheet.headerMergedRecords2.size(); i++) {
                    assertRecord(sheet.headerMergedRecords2.get(i), outSheet.headerMergedRecords2.get(i), errors);
                }
                
            }
            
            
        }
        
    }
    
    /**
     * 書き込みのテスト - 正規表現で一致
     * @since 1.1
     */
    @Test
    public void test_save_vr_regexLabel() throws Exception {
        
        // テストデータの作成
        RegexSheet outSheet = new RegexSheet();
        
        outSheet.addRecord1(new RegexSheet.ResultRecord().name("山田太郎").result("1回目", 40).result("2回目", 50).result("3回目", 60).comment("改善している。"));
        outSheet.addRecord2(new RegexSheet.ResultRecord().name("山田太郎").result("1回目", 40).result("2回目", 50).result("3回目", 60).comment("改善している。"));
        outSheet.addRecord3(new RegexSheet.ResultRecord().name("山田太郎").result("1回目", 40).result("2回目", 50).result("3回目", 60).comment("改善している。"));
        outSheet.addRecord4(new RegexSheet.ResultRecord().name("山田太郎").result("1回目", 40).result("2回目", 50).result("3回目", 60).comment("改善している。"));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true)
            .setRegexLabelText(true)
            .setNormalizeLabelText(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(RegexSheet.class);
            
            RegexSheet sheet = mapper.load(in, RegexSheet.class, errors);
            
            if(sheet.records1 != null) {
                assertThat(sheet.records1, hasSize(outSheet.records1.size()));
                
                for(int i=0; i < sheet.records1.size(); i++) {
                    assertRecord(sheet.records1.get(i), outSheet.records1.get(i), errors);
                }
                
            }
            
            if(sheet.records2 != null) {
                assertThat(sheet.records2, hasSize(outSheet.records2.size()));
                
                for(int i=0; i < sheet.records2.size(); i++) {
                    assertRecord(sheet.records2.get(i), outSheet.records2.get(i), errors);
                }
                
            }
            
            if(sheet.records3 != null) {
                assertThat(sheet.records3, hasSize(outSheet.records3.size()));
                
                for(int i=0; i < sheet.records3.size(); i++) {
                    assertRecord(sheet.records3.get(i), outSheet.records3.get(i), errors);
                }
                
            }
            
            if(sheet.records4 != null) {
                assertThat(sheet.records4, hasSize(outSheet.records4.size()));
                
                for(int i=0; i < sheet.records4.size(); i++) {
                    assertRecord(sheet.records4.get(i), outSheet.records4.get(i), errors);
                }
                
            }
            
            
        }
        
    }
    
    /**
     * 書き込みのテスト - ネストしたレコード
     * @since 1.4
     */
    @Test
    public void test_save_vr_nestedRecords() throws Exception {
        
        // テストデータの作成
        NestedSheet outSheet = new NestedSheet();
        
        // ○通常の表
        outSheet.addRecord1(new NestedSheet.LargeRecord().largeName("機能A").largeDescription("機能Aの説明")
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能A1").middleDescription("機能A1の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目A11").value("項目A11の値"))
                        .addRecord(new NestedSheet.SmallRecord().name("項目A12").value("項目A12の値")))
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能A2").middleDescription("機能A2の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目A21").value("項目A21の値")))
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能A3").middleDescription("機能A3の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目A31").value("項目A31の値"))
                        .addRecord(new NestedSheet.SmallRecord().name("項目A32").value("項目A32の値"))
                        .addRecord(new NestedSheet.SmallRecord().name("項目A33").value("項目A33の値"))));
        
        outSheet.addRecord1(new NestedSheet.LargeRecord().largeName("機能B").largeDescription("機能Bの説明")
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能B1").middleDescription("機能B1の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目B11").value("項目B11の値")))
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能B2").middleDescription("機能B2の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目B21").value("項目B21の値"))
                        .addRecord(new NestedSheet.SmallRecord().name("項目B22").value("項目B22の値"))));
        
        outSheet.addRecord1(new NestedSheet.LargeRecord().largeName("機能C").largeDescription("機能Cの説明")
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能C1").middleDescription("機能C1の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目C11").value("項目C11の値"))));
        
        // ○空のレコードがある表
        outSheet.addRecord2(new NestedSheet.LargeRecord().largeName("機能A").largeDescription("機能Aの説明")
                    .addRecord(new NestedSheet.MiddleRecord().middleName("機能A1").middleDescription("機能A1の説明")
                            .addRecord(new NestedSheet.SmallRecord().name("項目A11").value("項目A11の値"))
                            .addRecord(new NestedSheet.SmallRecord().name("項目A12").value("項目A12の値")))
                    .addRecord(new NestedSheet.MiddleRecord())
                    .addRecord(new NestedSheet.MiddleRecord().middleName("機能A2").middleDescription("機能A2の説明")
                            .addRecord(new NestedSheet.SmallRecord().name("項目A21").value("項目A21の値")))
                    .addRecord(new NestedSheet.MiddleRecord().middleName("機能A3").middleDescription("機能A3の説明")
                            .addRecord(new NestedSheet.SmallRecord().name("項目A31").value("項目A31の値"))
                            .addRecord(new NestedSheet.SmallRecord().name("項目A32").value("項目A32の値"))
                            .addRecord(new NestedSheet.SmallRecord().name("項目A33").value("項目A33の値"))));
        
        outSheet.addRecord2(new NestedSheet.LargeRecord());
        
        outSheet.addRecord2(new NestedSheet.LargeRecord().largeName("機能B").largeDescription("機能Bの説明")
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能B1").middleDescription("機能B1の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目B11").value("項目B11の値")))
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能B2").middleDescription("機能B2の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目B21").value("項目B21の値"))
                        .addRecord(new NestedSheet.SmallRecord())
                        .addRecord(new NestedSheet.SmallRecord().name("項目B22").value("項目B22の値"))));
        
        outSheet.addRecord2(new NestedSheet.LargeRecord().largeName("機能C").largeDescription("機能Cの説明")
                .addRecord(new NestedSheet.MiddleRecord().middleName("機能C1").middleDescription("機能C1の説明")
                        .addRecord(new NestedSheet.SmallRecord().name("項目C11").value("項目C11の値"))));
        
        
        // ○見出しが結合している
        outSheet.addRecord3(new NestedSheet.HeaderMergedLargeRecord().largeName("機能A").largeDescription("機能Aの説明")
                .addRecord(new NestedSheet.HeaderMergedMiddleRecord().middleName("機能A1").middleDescription("機能A1の説明")
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目A11").value("項目A11の値"))
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目A12").value("項目A12の値")))
                .addRecord(new NestedSheet.HeaderMergedMiddleRecord())
                .addRecord(new NestedSheet.HeaderMergedMiddleRecord().middleName("機能A2").middleDescription("機能A2の説明")
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目A21").value("項目A21の値")))
                .addRecord(new NestedSheet.HeaderMergedMiddleRecord().middleName("機能A3").middleDescription("機能A3の説明")
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目A31").value("項目A31の値"))
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目A32").value("項目A32の値"))
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目A33").value("項目A33の値"))));
        
        outSheet.addRecord3(new NestedSheet.HeaderMergedLargeRecord());
        
        outSheet.addRecord3(new NestedSheet.HeaderMergedLargeRecord().largeName("機能B").largeDescription("機能Bの説明")
                .addRecord(new NestedSheet.HeaderMergedMiddleRecord().middleName("機能B1").middleDescription("機能B1の説明")
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目B11").value("項目B11の値")))
                .addRecord(new NestedSheet.HeaderMergedMiddleRecord().middleName("機能B2").middleDescription("機能B2の説明")
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目B21").value("項目B21の値"))
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord())
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目B22").value("項目B22の値"))));
        
        outSheet.addRecord3(new NestedSheet.HeaderMergedLargeRecord().largeName("機能C").largeDescription("機能Cの説明")
                .addRecord(new NestedSheet.HeaderMergedMiddleRecord().middleName("機能C1").middleDescription("機能C1の説明")
                        .addRecord(new NestedSheet.HeaderMergedSmallRecord().name("項目C11").value("項目C11の値"))));
            
        // 一対一のネスト
        outSheet.addRecord(new NestedSheet.OneToOneRecord().no(1).className("A").name("山田太郎").birthday(toUtilDate(toTimestamp("2000-04-01 00:00:00.000")))
                .result(new NestedSheet.ResultRecord().kokugo(90).sansu(70).sum(160)));
        
        outSheet.addRecord(new NestedSheet.OneToOneRecord().no(2).className("A").name("鈴木次郎").birthday(toUtilDate(toTimestamp("2000-04-02 00:00:00.000")))
                .result(new NestedSheet.ResultRecord().kokugo(80).sansu(90).sum(170)));
        
        outSheet.addRecord(new NestedSheet.OneToOneRecord().no(3).className("B").name("山田太郎").birthday(toUtilDate(toTimestamp("2000-05-01 00:00:00.000")))
                .result(new NestedSheet.ResultRecord().kokugo(60).sansu(30).sum(90)));
        
        outSheet.addRecord(new NestedSheet.OneToOneRecord().no(4).className("B").name("山田花子").birthday(toUtilDate(toTimestamp("2000-05-02 00:00:00.000")))
                /*.result(new NestedSheet.ResultRecord().kokugo(90).sansu(70).sum(160))*/);
        
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(NestedSheet.class);
            
            NestedSheet sheet = mapper.load(in, NestedSheet.class, errors);
            
            if(sheet.largeRecords1 != null) {
                sheet.printRecord1();
                
                assertThat(sheet.largeRecords1, hasSize(3));
                for(int i=0; i < sheet.largeRecords1.size(); i++) {
                    assertRecord(sheet.largeRecords1.get(i), outSheet.largeRecords1.get(i), errors);
                }
            }
            
            if(sheet.largeRecords2 != null) {
                sheet.printRecord2();
                
                assertThat(sheet.largeRecords2, hasSize(3));
                
                // 空白があるので、再読込した値のみチェックする。
                for(NestedSheet.LargeRecord record : sheet.largeRecords2) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.largeRecords3 != null) {
                sheet.printRecord3();
                
                assertThat(sheet.largeRecords3, hasSize(3));
                
                // 空白があるので、再読込した値のみチェックする。
                for(NestedSheet.HeaderMergedLargeRecord record : sheet.largeRecords3) {
                    assertRecord(record, errors);
                }
            }
            
            if(sheet.oneToOneRecords != null) {
                assertThat(sheet.oneToOneRecords, hasSize(4));
                for(int i=0; i < sheet.oneToOneRecords.size(); i++) {
                    assertRecord(sheet.oneToOneRecords.get(i), outSheet.oneToOneRecords.get(i), errors);
                }
            }
        }
    }
    
    /**
     * 書き込みのテスト - 数式のテスト
     * @since 1.5
     */
    @Test
    public void test_save_vr_formula() throws Exception {
        // テストデータの作成
        FormulaSheet outSheet = new FormulaSheet();
        
        outSheet.add(new FormulaSheet.GradeRecord().name("山田太郎").sansu(90).kokugo(70));
        outSheet.add(new FormulaSheet.GradeRecord().name("鈴木次郎").sansu(80).kokugo(90));
        outSheet.add(new FormulaSheet.GradeRecord().name("平均"));
        
        
        outSheet.add(new FormulaSheet.EntryRecord()
                    .name("山田太郎")
                    .addDateAttended("4月1日", "出席").addDateAttended("4月2日", "出席").addDateAttended("出席可能数", "")
                    .comment("とりあえず出席します。"));
        
        outSheet.add(new FormulaSheet.EntryRecord()
                .name("鈴木次郎")
                .addDateAttended("4月1日", "欠席").addDateAttended("4月2日", "-").addDateAttended("4月3日", "出席").addDateAttended("出席可能数", "")
                .comment(""));
        
        // ファイルへの書き込み
        XlsMapper mapper = new XlsMapper();
        mapper.getConig().setContinueTypeBindFailure(true);
        
        File outFile = new File("src/test/out/anno_VerticalRecords_out.xlsx");
        try(InputStream template = new FileInputStream("src/test/data/anno_VerticalRecords_template.xlsx");
                OutputStream out = new FileOutputStream(outFile)) {
            
            mapper.save(template, out, outSheet);
        }
        
        // 書き込んだファイルを読み込み値の検証を行う。
        try(InputStream in = new FileInputStream(outFile)) {
            
            SheetBindingErrors errors = new SheetBindingErrors(FormulaSheet.class);
            
            FormulaSheet sheet = mapper.load(in, FormulaSheet.class, errors);
            
            if(sheet.gradeRecrods != null) {
                assertThat(sheet.gradeRecrods, hasSize(outSheet.gradeRecrods.size()));
                
                for(int i=0; i < sheet.gradeRecrods.size(); i++) {
                    assertRecord(sheet.gradeRecrods.get(i), outSheet.gradeRecrods.get(i), errors);
                }
                
            }
            
            if(sheet.entryRecords != null) {
                assertThat(sheet.entryRecords, hasSize(outSheet.entryRecords.size()));
                
                for(int i=0; i < sheet.entryRecords.size(); i++) {
                    assertRecord(sheet.entryRecords.get(i), outSheet.entryRecords.get(i), errors);
                }
                
            }
            
        }
        
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final NormalRecord inRecord, final NormalRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(outRecord.name));
        assertThat(inRecord.value, is(outRecord.value));
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final HeaderSpaceSheet.UserRecord inRecord, final HeaderSpaceSheet.UserRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(outRecord.name));
        assertThat(inRecord.tel, is(outRecord.tel));
        
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final MergedRecord inRecord, final MergedRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.category, is(outRecord.category));
        assertThat(inRecord.description, is(outRecord.description));
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final HeaderMergedRecord inRecord, final HeaderMergedRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(outRecord.name));
        assertThat(inRecord.mail, is(outRecord.mail));
        assertThat(inRecord.tel, is(outRecord.tel));
        assertThat(inRecord.comment, is(outRecord.comment));
        
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     * @param hasCell オプションのセルを持つかどうか。
     */
    private void assertRecord(final OptionalRecord inRecord, final OptionalRecord outRecord, final SheetBindingErrors errors, boolean hasCell) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(outRecord.name));
        assertThat(inRecord.value, is(outRecord.value));
        
        if(hasCell) {
            assertThat(inRecord.comment, is(outRecord.comment));
        } else {
            assertThat(inRecord.comment, is(nullValue()));
        }
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final ConvertedRecord inRecord, final ConvertedRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(inRecord.birthday, is(outRecord.birthday));
        assertThat(inRecord.age, is(outRecord.age));
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final MapRecord inRecord, final MapRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        if(inRecord.no == 1) {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.name, is(trim(outRecord.name)));
            
            Map<String, String> expected = new LinkedHashMap<>();
            expected.put("4月1日", "出席");
            expected.put("4月2日", "出席");
            expected.put("4月3日", null);
            
            assertThat(inRecord.dateAttended, is(expected));
            
        } else {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.name, is(trim(outRecord.name)));
            assertThat(inRecord.dateAttended, is(outRecord.dateAttended));
        }
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final MapConvertedRecord inRecord, final MapConvertedRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        if(inRecord.no == 1) {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.name, is(trim(outRecord.name)));
            
            Map<String, Boolean> expected = new LinkedHashMap<>();
            expected.put("4月1日", true);
            expected.put("4月2日", true);
            expected.put("4月3日", null);
            
            assertThat(inRecord.dateAttended, is(expected));
            
        } else {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.name, is(trim(outRecord.name)));
            assertThat(inRecord.dateAttended, is(outRecord.dateAttended));
        }
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final MapEndRecord inRecord, final MapEndRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        if(inRecord.no == 1) {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.name, is(trim(outRecord.name)));
            
            Map<String, String> expected = new LinkedHashMap<>();
            expected.put("4月1日", "出席");
            expected.put("4月2日", "出席");
            expected.put("4月3日", null);
            
            assertThat(inRecord.dateAttended, is(expected));
            assertThat(inRecord.dateAttended, not(hasKey("備考")));
            
            assertThat(inRecord.comment, is(outRecord.comment));
            
        } else {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.name, is(trim(outRecord.name)));
            assertThat(inRecord.dateAttended, is(outRecord.dateAttended));
            
            assertThat(inRecord.dateAttended, not(hasKey("備考")));
            assertThat(inRecord.comment, is(outRecord.comment));
        }
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final EmptySkipRecord inRecord, final EmptySkipRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(inRecord.birthday, is(outRecord.birthday));
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final RemainedOverRecord inRecord, final RemainedOverRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(inRecord.dateAttended, is(outRecord.dateAttended));
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final DataValidationRecord inRecord, final DataValidationRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        if(inRecord.no == 3) {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.selectRule, is(false));
            assertThat(inRecord.refRule, is(outRecord.refRule));
            
        } else {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.selectRule, is(outRecord.selectRule));
            assertThat(inRecord.refRule, is(outRecord.refRule));
        }
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final NameDefRecord inRecord, final NameDefRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.functionName, is(outRecord.functionName));
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final MethodAnnoRecord inRecord, final MethodAnnoRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(inRecord.birthday, is(outRecord.birthday));
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final MethodAnnoMapRecord inRecord, final MethodAnnoMapRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        if(inRecord.no == 1) {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.name, is(trim(outRecord.name)));
            
            Map<String, Boolean> expected = new LinkedHashMap<>();
            expected.put("4月1日", true);
            expected.put("4月2日", true);
            expected.put("4月3日", null);
            
            assertThat(inRecord.dateAttended, is(expected));
            
        } else {
            assertThat(inRecord.no, is(outRecord.no));
            assertThat(inRecord.name, is(trim(outRecord.name)));
            assertThat(inRecord.dateAttended, is(outRecord.dateAttended));
        }
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @since 1.1
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final StartedDataPositionSheet.DistantRecord inRecord, final StartedDataPositionSheet.DistantRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(inRecord.sansu, is(outRecord.sansu));
        assertThat(inRecord.kokugo, is(outRecord.kokugo));
        assertThat(inRecord.sum, is(outRecord.sum));
        
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @since 1.1
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final StartedDataPositionSheet.HeaderMergedRecord inRecord, final StartedDataPositionSheet.HeaderMergedRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(inRecord.sansu, is(outRecord.sansu));
        assertThat(inRecord.kokugo, is(outRecord.kokugo));
        assertThat(inRecord.sum, is(outRecord.sum));
        
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @since 1.1
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final RegexSheet.ResultRecord inRecord, final RegexSheet.ResultRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(inRecord.resultMap, is(outRecord.resultMap));
        assertThat(inRecord.comment, is(outRecord.comment));
        
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @since 1.4
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final NestedSheet.LargeRecord inLargeRecord, final NestedSheet.LargeRecord outLargeRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s largeName=%s\n",
                this.getClass().getSimpleName(), inLargeRecord.getClass().getSimpleName(), inLargeRecord.largeName);
        
        assertThat(inLargeRecord.largeName, is(outLargeRecord.largeName));
        assertThat(inLargeRecord.largeDescription, is(outLargeRecord.largeDescription));
        assertThat(inLargeRecord.labels.get("largeName"), is(outLargeRecord.labels.get("largeName")));
        assertThat(inLargeRecord.labels.get("largeDescription"), is(outLargeRecord.labels.get("largeDescription")));
        
        if(inLargeRecord.middleRecords != null) {
            assertThat(inLargeRecord.middleRecords, hasSize(outLargeRecord.middleRecords.size()));
            
            for(int i=0; i < inLargeRecord.middleRecords.size(); i++) {
                
                final NestedSheet.MiddleRecord inMiddleRecord = inLargeRecord.middleRecords.get(i);
                final NestedSheet.MiddleRecord outMiddleRecord = outLargeRecord.middleRecords.get(i);
                
                assertThat(inMiddleRecord.middleName, is(outMiddleRecord.middleName));
                assertThat(inMiddleRecord.middleDescription, is(outMiddleRecord.middleDescription));
                assertThat(inMiddleRecord.labels.get("middleName"), is(outMiddleRecord.labels.get("middleName")));
                assertThat(inMiddleRecord.labels.get("middleDescription"), is(outMiddleRecord.labels.get("middleDescription")));
                
                if(inMiddleRecord.smallRecords != null) {
                    assertThat(inMiddleRecord.smallRecords, arrayWithSize(outMiddleRecord.smallRecords.length));
                    
                    for(int j=0; j < inMiddleRecord.smallRecords.length; j++) {
                        final NestedSheet.SmallRecord inSmallRecord = inMiddleRecord.smallRecords[j];
                        final NestedSheet.SmallRecord outSmallRecord = outMiddleRecord.smallRecords[j];
                        
                        assertThat(inSmallRecord.name, is(outSmallRecord.name));
                        assertThat(inSmallRecord.value, is(outSmallRecord.value));
                        assertThat(inSmallRecord.labels.get("name"), is(outSmallRecord.labels.get("name")));
                        assertThat(inSmallRecord.labels.get("value"), is(outSmallRecord.labels.get("value")));
                    }
                    
                }
            }
        }
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @since 1.4
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final NestedSheet.OneToOneRecord inRecord, final NestedSheet.OneToOneRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(inRecord.birthday, is(outRecord.birthday));
        
        if(inRecord.no == 4) {
            assertThat(inRecord.result, is(nullValue()));
            assertThat(inRecord.result, is(outRecord.result));
            
        } else {
            assertThat(inRecord.result.sansu, is(outRecord.result.sansu));
            assertThat(inRecord.result.kokugo, is(outRecord.result.kokugo));
            assertThat(inRecord.result.sum, is(outRecord.result.sum));
            
        }
        
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @since 1.5
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final FormulaSheet.GradeRecord inRecord, final FormulaSheet.GradeRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        
        if(inRecord.name.equals("平均")) {
            assertThat(inRecord.sansu, is(85));
            assertThat(inRecord.kokugo, is(80));
            
        } else {
            assertThat(inRecord.sansu, is(outRecord.sansu));
            assertThat(inRecord.kokugo, is(outRecord.kokugo));
            
        }
        
        assertThat(inRecord.sum, is(inRecord.sansu + inRecord.kokugo));
        
    }
    
    /**
     * 書き込んだレコードを検証するための
     * @since 1.5
     * @param inRecord
     * @param outRecord
     * @param errors
     */
    private void assertRecord(final FormulaSheet.EntryRecord inRecord, final FormulaSheet.EntryRecord outRecord, final SheetBindingErrors errors) {
        
        System.out.printf("%s - assertRecord::%s no=%d\n",
                this.getClass().getSimpleName(), inRecord.getClass().getSimpleName(), inRecord.no);
        
        assertThat(inRecord.no, is(outRecord.no));
        assertThat(inRecord.name, is(trim(outRecord.name)));
        assertThat(trim(inRecord.comment), is(trim(outRecord.comment)));
        
        Map<String, String> expected = new LinkedHashMap<>();
        expected.put("4月1日", outRecord.dateAttended.get("4月1日"));
        expected.put("4月2日", outRecord.dateAttended.get("4月2日"));
        expected.put("4月3日", outRecord.dateAttended.get("4月3日"));
        
        if(inRecord.no == 1) {
            expected.put("出席可能数", "2");
        } else if(inRecord.no == 2) {
            expected.put("出席可能数", "1");
        }
        
        assertThat(inRecord.dateAttended, is(expected));
        
    }
    
    /**
     * 開始位置の指定
     *
     */
    @XlsSheet(name="開始位置の指定")
    private static class StartedPositionSheet {
        
        @XlsHint(order=1)
        @XlsVerticalRecords(tableLabel="○×一覧", ignoreEmptyRecord=true)
        private List<NormalRecord> normalRecords1;
        
        @XlsHint(order=2)
        @XlsVerticalRecords(headerAddress="C9", ignoreEmptyRecord=true)
        private List<NormalRecord> normalRecords2;
        
        @XlsHint(order=3)
        @XlsVerticalRecords(headerColumn=3, headerRow=13, ignoreEmptyRecord=true)
        private List<NormalRecord> normalRecords3;
        
//        @XlsHint(order=4)
//        @XlsVerticalRecords(tableLabel="◆△一覧", bottom=2, skipEmptyRecord=true)
        private List<NormalRecord> normalRecords4;
        
        @XlsHint(order=5)
        @XlsVerticalRecords(tableLabel="存在しない", optional=true, ignoreEmptyRecord=true)
        private List<NormalRecord> normalRecords5;
        
        /**
         * noを自動的に付与する。
         * @param record
         * @return 自身のインスタンス
         */
        public StartedPositionSheet add1(NormalRecord record) {
            if(normalRecords1 == null) {
                this.normalRecords1 = new ArrayList<>();
            }
            
            this.normalRecords1.add(record);
            record.no(normalRecords1.size());
            
            return this;
        }
        
        /**
         * noを自動的に付与する。
         * @param record
         * @return 自身のインスタンス
         */
        public StartedPositionSheet add2(NormalRecord record) {
            if(normalRecords2 == null) {
                this.normalRecords2 = new ArrayList<>();
            }
            
            this.normalRecords2.add(record);
            record.no(normalRecords2.size());
            
            return this;
        }
        
        /**
         * noを自動的に付与する。
         * @param record
         * @return 自身のインスタンス
         */
        public StartedPositionSheet add3(NormalRecord record) {
            if(normalRecords3 == null) {
                this.normalRecords3 = new ArrayList<>();
            }
            
            this.normalRecords3.add(record);
            record.no(normalRecords3.size());
            
            return this;
        }
        
        /**
         * noを自動的に付与する。
         * @param record
         * @return 自身のインスタンス
         */
        public StartedPositionSheet add4(NormalRecord record) {
            if(normalRecords4 == null) {
                this.normalRecords4 = new ArrayList<>();
            }
            
            this.normalRecords4.add(record);
            record.no(normalRecords4.size());
            
            return this;
        }
        
        /**
         * noを自動的に付与する。
         * @param record
         * @return 自身のインスタンス
         */
        public StartedPositionSheet add5(NormalRecord record) {
            if(normalRecords5 == null) {
                this.normalRecords5 = new ArrayList<>();
            }
            
            this.normalRecords5.add(record);
            record.no(normalRecords5.size());
            
            return this;
        }
    }
    
   /**
    * 開始位置の指定 - 見出し指定で見つからない場合
    *
    */
   @XlsSheet(name="開始位置の指定")
   private static class StartedPositionError1Sheet {
       
       @XlsVerticalRecords(tableLabel="存在しない", optional=false)
       private List<NormalRecord> normalRecords5;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public StartedPositionError1Sheet add(NormalRecord record) {
           if(normalRecords5 == null) {
               this.normalRecords5 = new ArrayList<>();
           }
           
           this.normalRecords5.add(record);
           record.no(normalRecords5.size());
           
           return this;
       }
   }
   
   /**
    * 開始位置の指定 - アドレス指定でフォーマット不正な場合
    *
    */
   @XlsSheet(name="開始位置の指定")
   private static class StartedPositionError2Sheet {
       
       @XlsVerticalRecords(headerAddress="_C7")
       private List<NormalRecord> normalRecords2;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public StartedPositionError2Sheet add(NormalRecord record) {
           if(normalRecords2 == null) {
               this.normalRecords2 = new ArrayList<>();
           }
           
           this.normalRecords2.add(record);
           record.no(normalRecords2.size());
           
           return this;
       }
   }
   
   /**
    * 開始位置の指定 - アドレス指定でインデックスが不正な場合
    *
    */
   @XlsSheet(name="開始位置の指定")
   private static class StartedPositionError3Sheet {
       
       @XlsVerticalRecords(headerColumn=-1, headerRow=-1)
       private List<NormalRecord> normalRecords3;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public StartedPositionError3Sheet add(NormalRecord record) {
           if(normalRecords3 == null) {
               this.normalRecords3 = new ArrayList<>();
           }
           
           this.normalRecords3.add(record);
           record.no(normalRecords3.size());
           
           return this;
       }
   }
   
   /**
    * 表の終了位置の指定
    *
    */
   @XlsSheet(name="終了位置の指定")
   private static class EndPositionSheet {
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="終端レコードの指定（Empty）", terminal=RecordTerminal.Empty)
       private List<NormalRecord> normalRecords1;
       
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="終端レコードの指定（Border）", terminal=RecordTerminal.Border)
       private List<NormalRecord> normalRecords2;
       
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="終端セルの指定", terminal=RecordTerminal.Border, terminateLabel="合計")
       private List<NormalRecord> normalRecords3;
       
       @XlsHint(order=4)
       @XlsVerticalRecords(tableLabel="見出しセルの個数指定", terminal=RecordTerminal.Border, headerLimit=3)
       private List<NormalRecord> normalRecords4;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public EndPositionSheet add1(NormalRecord record) {
           if(normalRecords1 == null) {
               this.normalRecords1 = new ArrayList<>();
           }
           
           this.normalRecords1.add(record);
           record.no(normalRecords1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public EndPositionSheet add2(NormalRecord record) {
           if(normalRecords2 == null) {
               this.normalRecords2 = new ArrayList<>();
           }
           
           this.normalRecords2.add(record);
           record.no(normalRecords2.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public EndPositionSheet add3(NormalRecord record) {
           if(normalRecords3 == null) {
               this.normalRecords3 = new ArrayList<>();
           }
           
           this.normalRecords3.add(record);
           record.no(normalRecords3.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public EndPositionSheet add4(NormalRecord record) {
           if(normalRecords4 == null) {
               this.normalRecords4 = new ArrayList<>();
           }
           
           this.normalRecords4.add(record);
           record.no(normalRecords4.size());
           
           return this;
       }
   }
   
   private static class NormalRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="名称")
       private String name;
       
       @XlsColumn(columnName="値")
       private Double value;
       
       public NormalRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public NormalRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public NormalRecord value(Double value) {
           this.value = value;
           return this;
       }
   }
   
   /**
    * 見出しに空白がある場合のシート
    *
    */
   @XlsSheet(name="見出しの空白")
   private static class HeaderSpaceSheet {
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="見出しに空白なし", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<UserRecord> records1;
       
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="見出しが結合", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<UserRecord> records2;
       
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="見出しに空白がある", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy, range=2)
       private List<UserRecord> records3;
       
       @XlsHint(order=4)
       @XlsVerticalRecords(tableLabel="開始位置がずれている", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy, range=3)
       private List<UserRecord> records4;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public HeaderSpaceSheet addRecord1(UserRecord record) {
           if(records1 == null) {
               this.records1 = new ArrayList<>();
           }
           
           this.records1.add(record);
           record.no(records1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public HeaderSpaceSheet addRecord2(UserRecord record) {
           if(records2 == null) {
               this.records2 = new ArrayList<>();
           }
           
           this.records2.add(record);
           record.no(records2.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public HeaderSpaceSheet addRecord3(UserRecord record) {
           if(records3 == null) {
               this.records3 = new ArrayList<>();
           }
           
           this.records3.add(record);
           record.no(records3.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public HeaderSpaceSheet addRecord4(UserRecord record) {
           if(records4 == null) {
               this.records4 = new ArrayList<>();
           }
           
           this.records4.add(record);
           record.no(records4.size());
           
           return this;
       }
       
       private static class UserRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="No.")
           int no;
           
           @XlsColumn(columnName="氏名")
           String name;
           
           @XlsColumn(columnName="電話番号")
           String tel;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "no");
           }
           
           public UserRecord no(int no) {
               this.no = no;
               return this;
           }
           
           public UserRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public UserRecord tel(String tel) {
               this.tel = tel;
               return this;
           }
       }
       
   }
   
   /**
    * カラムの様々な指定のシート
    */
   @XlsSheet(name="カラムの設定")
   private static class ColumnSettingSheet {
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="結合セル", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<MergedRecord> mergedRecords;
       
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="見出しが結合", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<HeaderMergedRecord> headerMergedRecords;
       
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="オプションのセル（セルがある）", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<OptionalRecord> optionalRecords1;
       
       @XlsHint(order=4)
       @XlsVerticalRecords(tableLabel="オプションのセル（セルがない）", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<OptionalRecord> optionalRecords2;
       
       @XlsHint(order=5)
       @XlsVerticalRecords(tableLabel="Converterがある", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<ConvertedRecord> convertedRecord;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ColumnSettingSheet addMerged(MergedRecord record) {
           if(mergedRecords == null) {
               this.mergedRecords = new ArrayList<>();
           }
           
           this.mergedRecords.add(record);
           record.no(mergedRecords.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ColumnSettingSheet addHeaderMerged(HeaderMergedRecord record) {
           if(headerMergedRecords == null) {
               this.headerMergedRecords = new ArrayList<>();
           }
           
           this.headerMergedRecords.add(record);
           record.no(headerMergedRecords.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ColumnSettingSheet addOptional1(OptionalRecord record) {
           if(optionalRecords1 == null) {
               this.optionalRecords1 = new ArrayList<>();
           }
           
           this.optionalRecords1.add(record);
           record.no(optionalRecords1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ColumnSettingSheet addOptional2(OptionalRecord record) {
           if(optionalRecords2 == null) {
               this.optionalRecords2 = new ArrayList<>();
           }
           
           this.optionalRecords2.add(record);
           record.no(optionalRecords2.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ColumnSettingSheet addConverted(ConvertedRecord record) {
           if(convertedRecord == null) {
               this.convertedRecord = new ArrayList<>();
           }
           
           this.convertedRecord.add(record);
           record.no(convertedRecord.size());
           
           return this;
       }
   }
   
   /**
    * 結合したセルのレコード
    */
   private static class MergedRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="分類", merged=true)
       private Category category;
       
       @XlsColumn(columnName="説明", merged=true)
       private String description;
       
       public MergedRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public MergedRecord category(Category category) {
           this.category = category;
           return this;
       }
       
       public MergedRecord description(String description) {
           this.description = description;
           return this;
       }
   }
   
   private enum Category {
       Info, Warn, Error
       ;
   }
   
   /**
    * 見出し用セルが結合されている場合
    */
   private static class HeaderMergedRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="氏名")
       private String name;
       
       @XlsColumn(columnName="連絡先")
       private String mail;
       
       @XlsColumn(columnName="連絡先", headerMerged=1)
       private String tel;
       
       @XlsColumn(columnName="備考")
       private String comment;
       
       public HeaderMergedRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public HeaderMergedRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public HeaderMergedRecord mail(String mail) {
           this.mail = mail;
           return this;
       }
       
       public HeaderMergedRecord tel(String tel) {
           this.tel = tel;
           return this;
       }
       
       public HeaderMergedRecord comment(String comment) {
           this.comment = comment;
           return this;
       }
       
   }
   
   /**
    * オプションのセル
    */
   private static class OptionalRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="名称")
       private String name;
       
       @XlsColumn(columnName="値")
       private Double value;
       
       @XlsColumn(columnName="備考", optional=true)
       private String comment;
       
       public OptionalRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public OptionalRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public OptionalRecord value(Double value) {
           this.value = value;
           return this;
       }
       
       public OptionalRecord comment(String comment) {
           this.comment = comment;
           return this;
       }
   }
   
   /**
    * Converterの設定がある
    *
    */
   private static class ConvertedRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsConverter(trim=true)
       @XlsColumn(columnName="氏名")
       private String name;
       
       @XlsDateConverter(javaPattern="yyyy年M月d日", lenient=true)
       @XlsColumn(columnName="生年月日")
       private Date birthday;
       
       @XlsColumn(columnName="年齢")
       private int age;
       
       public ConvertedRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public ConvertedRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public ConvertedRecord birthday(Date birthday) {
           this.birthday = birthday;
           return this;
       }
       
       public ConvertedRecord age(int age) {
           this.age = age;
           return this;
       }
       
   }
   
   /**
    * {@link XlsMapColumns}を使用したシート
    *
    */
   @XlsSheet(name="マップカラムの設定")
   private static class MapColumnSettingSheet {
       
       @XlsVerticalRecords(tableLabel="マップカラム（文字列）", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<MapRecord> mapRecords1;
       
       @XlsVerticalRecords(tableLabel="マップカラム（Converterあり）", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<MapConvertedRecord> mapRecords2;
       
       @XlsVerticalRecords(tableLabel="マップカラム（終了条件がある）", terminal=RecordTerminal.Border,
               overRecord=OverRecordOperate.Copy)
       private List<MapEndRecord> mapRecords3;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public MapColumnSettingSheet add(MapRecord record) {
           if(mapRecords1 == null) {
               this.mapRecords1 = new ArrayList<>();
           }
           
           this.mapRecords1.add(record);
           record.no(mapRecords1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public MapColumnSettingSheet add(MapConvertedRecord record) {
           if(mapRecords2 == null) {
               this.mapRecords2 = new ArrayList<>();
           }
           
           this.mapRecords2.add(record);
           record.no(mapRecords2.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public MapColumnSettingSheet add(MapEndRecord record) {
           if(mapRecords3 == null) {
               this.mapRecords3 = new ArrayList<>();
           }
           
           this.mapRecords3.add(record);
           record.no(mapRecords3.size());
           
           return this;
       }
       
   }
   
   /**
    * マップのセル（文字列）
    */
   private static class MapRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="氏名")
       private String name;
       
       @XlsMapColumns(previousColumnName="氏名")
       private Map<String, String> dateAttended;
       
       public MapRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public MapRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public MapRecord dateAttended(Map<String, String> dateAttended) {
           this.dateAttended = dateAttended;
           return this;
       }
       
       public MapRecord addDateAttended(final String key, final String value) {
           if(dateAttended == null) {
               this.dateAttended = new LinkedHashMap<>();
           }
           
           this.dateAttended.put(key, value);
           
           return this;
       }
   }
   
   /**
    * マップのセル（Converterあり）
    */
   private static class MapConvertedRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="氏名")
       private String name;
       
       @XlsBooleanConverter(loadForTrue="出席", loadForFalse="欠席", saveAsTrue="出席", saveAsFalse="欠席")
       @XlsMapColumns(previousColumnName="氏名")
       private Map<String, Boolean> dateAttended;
       
       public MapConvertedRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public MapConvertedRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public MapConvertedRecord dateAttended(Map<String, Boolean> dateAttended) {
           this.dateAttended = dateAttended;
           return this;
       }
       
       public MapConvertedRecord addDateAttended(final String key, final Boolean value) {
           if(dateAttended == null) {
               this.dateAttended = new LinkedHashMap<>();
           }
           
           this.dateAttended.put(key, value);
           
           return this;
       }
   }
   
   /**
    * マップのセル（終了条件がある）
    */
   private static class MapEndRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="氏名")
       private String name;
       
       @XlsMapColumns(previousColumnName="氏名", nextColumnName="備考")
       private Map<String, String> dateAttended;
       
       @XlsColumn(columnName="備考")
       private String comment;
       
       public MapEndRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public MapEndRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public MapEndRecord dateAttended(Map<String, String> dateAttended) {
           this.dateAttended = dateAttended;
           return this;
       }
       
       public MapEndRecord addDateAttended(final String key, final String value) {
           if(dateAttended == null) {
               this.dateAttended = new LinkedHashMap<>();
           }
           
           this.dateAttended.put(key, value);
           
           return this;
       }
       
       public MapEndRecord comment(String comment) {
           this.comment = comment;
           return this;
       }
   }
   
   /**
    * 様々なレコードの設定
    */
   @XlsSheet(name="レコードの設定")
   private static class RecodSettingSheet {
       
       /**
        * 空のレコードをスキップ（リスト）
        */
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="名簿（リスト）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Copy)
       private List<EmptySkipRecord> skipList;
       
       /**
        * 空のレコードをスキップ（集合）
        */
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="名簿（集合）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Copy)
       private Set<EmptySkipRecord> skipSet;
       
       /**
        * 配列
        */
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="名簿（配列）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Copy)
       private EmptySkipRecord[] skipArray;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RecodSettingSheet addList(EmptySkipRecord record) {
           if(skipList == null) {
               this.skipList = new ArrayList<>();
           }
           
           this.skipList.add(record);
           record.no(skipList.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RecodSettingSheet addSet(EmptySkipRecord record) {
           if(skipSet == null) {
               this.skipSet = new LinkedHashSet<>();
           }
           
           this.skipSet.add(record);
           record.no(skipSet.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RecodSettingSheet addArray(EmptySkipRecord record) {
           
           final List<EmptySkipRecord> list;
           if(skipArray == null) {
               list = new ArrayList<>();
           } else {
               list = new ArrayList<>(Arrays.asList(skipArray));
           }
           
           list.add(record);
           record.no(list.size());
           
           this.skipArray = list.toArray(new EmptySkipRecord[list.size()]);
           
           return this;
       }
   }
   
   /**
    * 空のレコードをスキップ可能なレコード
    *
    */
   private static class EmptySkipRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="氏名")
       private String name;
       
       @XlsDateConverter(javaPattern="yyyy年M月d日", excelPattern="yyyy\"年\"m\"月\"d\"日\"")
       @XlsColumn(columnName="生年月日")
       private Date birthday;
       
       @XlsIsEmpty
       public boolean isEmpty() {
           return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "no");
       }
       
       public EmptySkipRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public EmptySkipRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public EmptySkipRecord birthday(Date birthday) {
           this.birthday = birthday;
           return this;
       }
   }
   
   /**
    * 余分／不足なレコード制御用のシート
    *
    */
   @XlsSheet(name="余分なレコードの制御")
   private static class RemainedOverSheet {
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="足りないレコード（Break）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Break)
       private List<RemainedOverRecord> overBreakRecrods;
       
//       @XlsHint(order=2)
//       @XlsVerticalRecords(tableLabel="足りないレコード（Insert）", terminal=RecordTerminal.Border, skipEmptyRecord=true,
//               overRecord=OverRecordOperate.Insert)
       private List<RemainedOverRecord> overInsertRecrods;
       
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="足りないレコード（Copy）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Copy)
       private List<RemainedOverRecord> overCopyRecrods;
       
       @XlsHint(order=4)
       @XlsVerticalRecords(tableLabel="余分なレコード（None）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               remainedRecord=RemainedRecordOperate.None)
       private List<RemainedOverRecord> remainedNoneRecrods;
       
       @XlsHint(order=5)
       @XlsVerticalRecords(tableLabel="余分なレコード（Clear）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               remainedRecord=RemainedRecordOperate.Clear)
       private List<RemainedOverRecord> remainedClearRecrods;
       
//       @XlsHint(order=6)
//       @XlsVerticalRecords(tableLabel="余分なレコード（Delete）", terminal=RecordTerminal.Border, skipEmptyRecord=true,
//               remainedRecord=RemainedRecordOperate.Delete)
       private List<RemainedOverRecord> remainedDeleteRecrods1;
       
//       @XlsHint(order=7)
//       @XlsVerticalRecords(tableLabel="余分なレコード（Delete）（データなし）", terminal=RecordTerminal.Border, skipEmptyRecord=true,
//               remainedRecord=RemainedRecordOperate.Delete)
       private List<RemainedOverRecord> remainedDeleteRecrods2;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RemainedOverSheet addOverBreak(RemainedOverRecord record) {
           if(overBreakRecrods == null) {
               this.overBreakRecrods = new ArrayList<>();
           }
           
           this.overBreakRecrods.add(record);
           record.no(overBreakRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RemainedOverSheet addOverInsert(RemainedOverRecord record) {
           if(overInsertRecrods == null) {
               this.overInsertRecrods = new ArrayList<>();
           }
           
           this.overInsertRecrods.add(record);
           record.no(overInsertRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RemainedOverSheet addOverCopy(RemainedOverRecord record) {
           if(overCopyRecrods == null) {
               this.overCopyRecrods = new ArrayList<>();
           }
           
           this.overCopyRecrods.add(record);
           record.no(overCopyRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RemainedOverSheet addRemainedNone(RemainedOverRecord record) {
           if(remainedNoneRecrods == null) {
               this.remainedNoneRecrods = new ArrayList<>();
           }
           
           this.remainedNoneRecrods.add(record);
           record.no(remainedNoneRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RemainedOverSheet addRemainedClear(RemainedOverRecord record) {
           if(remainedClearRecrods == null) {
               this.remainedClearRecrods = new ArrayList<>();
           }
           
           this.remainedClearRecrods.add(record);
           record.no(remainedClearRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RemainedOverSheet addRemainedDelete1(RemainedOverRecord record) {
           if(remainedDeleteRecrods1 == null) {
               this.remainedDeleteRecrods1 = new ArrayList<>();
           }
           
           this.remainedDeleteRecrods1.add(record);
           record.no(remainedDeleteRecrods1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RemainedOverSheet addRemmainedDelete2(RemainedOverRecord record) {
           if(remainedDeleteRecrods2 == null) {
               this.remainedDeleteRecrods2 = new ArrayList<>();
           }
           
           this.remainedDeleteRecrods2.add(record);
           record.no(remainedDeleteRecrods2.size());
           
           return this;
       }
   }
   
   private static class RemainedOverRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="氏名")
       private String name;
       
       @XlsMapColumns(previousColumnName="氏名")
       private Map<String, String> dateAttended;
       
       @XlsIsEmpty
       public boolean isEmpty() {
           IsEmptyBuilder builder = new IsEmptyBuilder(IsEmptyConfig.create().withZeroAsEmpty(true));
           builder.append(name);
           builder.compare(new IsEmptyComparator() {
               
               @Override
               public boolean isEmpty() {
                   if(dateAttended == null || dateAttended.isEmpty()) {
                       return true;
                   }
                   
                   for(String value : dateAttended.values()) {
                       if(Utils.isNotEmpty(value)) {
                           return false;
                       }
                   }
                   
                   return true;
               }
           });
           
           return builder.isEmpty();
       }
       
       public RemainedOverRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public RemainedOverRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public RemainedOverRecord dateAttended(Map<String, String> dateAttended) {
           this.dateAttended = dateAttended;
           return this;
       }
       
       public RemainedOverRecord addDateAttended(final String key, final String value) {
           if(dateAttended == null) {
               this.dateAttended = new LinkedHashMap<>();
           }
           
           this.dateAttended.put(key, value);
           
           return this;
       }
       
   }
   
   /**
    * 入力規則用のシート
    */
   @XlsSheet(name="オプション設定（入力規則）")
   private static class ValidationRuleSheet {
       
       /**
        * 入力定義が設定されてた表（挿入用）
        */
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="入力規則（レコードの挿入）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Copy)
       private List<DataValidationRecord> insertValidationRecrods;
       
       /**
        * 名前の定義用の表
        */
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="名前の定義", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Copy)
       private List<NameDefRecord> nameRecords;
       
       /**
        * 入力定義が設定されてた表（削除用）
        */
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="入力規則（レコードの削除）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Break, remainedRecord=RemainedRecordOperate.Clear)
       private List<DataValidationRecord> deleteValidationRecrods;
       
       /**
        * 入力定義が設定されてた表（削除用）（データなし）
        */
       @XlsHint(order=4)
       @XlsVerticalRecords(tableLabel="入力規則（レコードの削除）（データなし）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Break, remainedRecord=RemainedRecordOperate.Clear)
       private List<DataValidationRecord> nonDeleteValidationRecrods;
       
       /**
        * 入力定義が設定されてた表（コピー用）
        */
       @XlsHint(order=5)
       @XlsVerticalRecords(tableLabel="入力規則（レコードのコピー）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true,
               overRecord=OverRecordOperate.Copy, remainedRecord=RemainedRecordOperate.Clear)
       private List<DataValidationRecord> copyValidationRecrods;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ValidationRuleSheet addInsert(DataValidationRecord record) {
           if(insertValidationRecrods == null) {
               this.insertValidationRecrods = new ArrayList<>();
           }
           
           this.insertValidationRecrods.add(record);
           record.no(insertValidationRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ValidationRuleSheet addDelete(DataValidationRecord record) {
           if(deleteValidationRecrods == null) {
               this.deleteValidationRecrods = new ArrayList<>();
           }
           
           this.deleteValidationRecrods.add(record);
           record.no(deleteValidationRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ValidationRuleSheet addCopy(DataValidationRecord record) {
           if(copyValidationRecrods == null) {
               this.copyValidationRecrods = new ArrayList<>();
           }
           
           this.copyValidationRecrods.add(record);
           record.no(copyValidationRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public ValidationRuleSheet add(NameDefRecord record) {
           if(nameRecords == null) {
               this.nameRecords = new ArrayList<>();
           }
           
           this.nameRecords.add(record);
           record.no(nameRecords.size());
           
           return this;
       }
   }
   
   /**
    * 入力規則を設定されたレコード
    *
    */
   private static class DataValidationRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsBooleanConverter(loadForTrue="○", loadForFalse={"×", "-", ""}, saveAsTrue="○", saveAsFalse="×")
       @XlsColumn(columnName="リスト形式")
       private Boolean selectRule;
       
       @XlsColumn(columnName="参照形式")
       private String refRule;
       
       @XlsBooleanConverter(loadForTrue="レ", loadForFalse={"-", ""}, saveAsTrue="レ", saveAsFalse="-")
       @XlsMapColumns(previousColumnName="参照形式")
       private Map<String, Boolean> category;
       
       @XlsIsEmpty
       public boolean isEmpty() {
           IsEmptyBuilder builder = new IsEmptyBuilder(IsEmptyConfig.create().withZeroAsEmpty(true));
           builder.append(selectRule);
           builder.compare(new IsEmptyComparator() {
               
               @Override
               public boolean isEmpty() {
                   if(category == null || category.isEmpty()) {
                       return true;
                   }
                   
                   for(Boolean value : category.values()) {
                       if(value != null && value == true) {
                           return false;
                       }
                   }
                   
                   return true;
               }
           });
           
           return builder.isEmpty();
       }
       
       public DataValidationRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public DataValidationRecord selectRule(Boolean selectRule) {
           this.selectRule = selectRule;
           return this;
       }
       
       public DataValidationRecord refRule(String refRule) {
           this.refRule = refRule;
           return this;
       }
       
       public DataValidationRecord category(Map<String, Boolean> category) {
           this.category = category;
           return this;
       }
       
       public DataValidationRecord addCategory(final String key, final Boolean value) {
           if(category == null) {
               this.category = new LinkedHashMap<>();
           }
           
           this.category.put(key, value);
           
           return this;
       }
       
   }
   
   /**
    * 名前の定義用のレコード
    *
    */
   private static class NameDefRecord {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsColumn(columnName="No.")
       private int no;
       
       @XlsColumn(columnName="機能名")
       private String functionName;
       
       public NameDefRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public NameDefRecord functionName(String functionName) {
           this.functionName = functionName;
           return this;
       }
       
   }
   
   /**
    * メソッドにアノテーションを付与するシート
    * @since 1.0
    *
    */
   @XlsSheet(name="メソッドにアノテーションを設定")
   private static class MethodAnnoSheet {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       private List<MethodAnnoRecord> records;
       
       private MethodAnnoMapRecord[] mapRecords;
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="名簿", overRecord=OverRecordOperate.Copy)
       public List<MethodAnnoRecord> getRecords() {
           return records;
       }
       
       @XlsVerticalRecords(tableLabel="名簿", ignoreEmptyRecord=true)
       public void setRecords(List<MethodAnnoRecord> records) {
           this.records = records;
       }
       
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="出欠", overRecord=OverRecordOperate.Copy)
       public MethodAnnoMapRecord[] getMapRecords() {
           return mapRecords;
       }
       
       @XlsVerticalRecords(tableLabel="出欠", ignoreEmptyRecord=true)
       public void setMapRecords(MethodAnnoMapRecord[] mapRecords) {
           this.mapRecords = mapRecords;
       }
       
       public MethodAnnoSheet add(MethodAnnoRecord record) {
           if(records == null) {
               this.records = new ArrayList<>();
           }
           
           this.records.add(record);
           record.no(records.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public MethodAnnoSheet add(MethodAnnoMapRecord record) {
           
           final List<MethodAnnoMapRecord> list;
           if(mapRecords == null) {
               list = new ArrayList<>();
           } else {
               list = new ArrayList<>(Arrays.asList(mapRecords));
           }
           
           list.add(record);
           record.no(list.size());
           
           this.mapRecords = list.toArray(new MethodAnnoMapRecord[list.size()]);
           
           return this;
       }
   }
   
   /**
    * メソッドにアノテーションを付与したレコード
    * @since 1.0
    */
   public static class MethodAnnoRecord {
       
       private int no;
       
       private String name;
       
       private Date birthday;
       
       private Point noPosition;
       
       private Point namePosition;
       
       private Point birthdayPosition;
       
       private String noLabel;
       
       private String nameLabel;
       
       private String birthdayLabel;
       
       @XlsColumn(columnName="No.")
       public int getNo() {
           return no;
       }
       
       @XlsColumn(columnName="No.")
       public void setNo(int no) {
           this.no = no;
       }
       
       @XlsColumn(columnName="氏名")
       public String getName() {
           return name;
       }
       
       @XlsColumn(columnName="氏名")
       public void setName(String name) {
           this.name = name;
       }
       
       @XlsColumn(columnName="生年月日")
       public Date getBirthday() {
           return birthday;
       }
       
       @XlsDateConverter(javaPattern="yyyy年M月d日")
       @XlsColumn(columnName="生年月日")
       public void setBirthday(Date birthday) {
           this.birthday = birthday;
       }
       
       public void setNoPosition(int x, int y) {
           this.noPosition = new Point(x, y);
       }
       
       public void setNamePosition(int x, int y) {
           this.namePosition = new Point(x, y);
       }
       
       public void setBirthdayPosition(int x, int y) {
           this.birthdayPosition = new Point(x, y);
       }
       
       @XlsIsEmpty
       public boolean isEmpty() {
           return new IsEmptyBuilder()
               .append(name)
               .append(birthday)
               .isEmpty();
       }
       
       public MethodAnnoRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public MethodAnnoRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public MethodAnnoRecord birthday(Date birthday) {
           this.birthday = birthday;
           return this;
       }
       
       public void setNoLabel(String noLabel) {
           this.noLabel = noLabel;
       }
       
       public void setNameLabel(String nameLabel) {
           this.nameLabel = nameLabel;
       }
       
       public void setBirthdayLabel(String birthdayLabel) {
           this.birthdayLabel = birthdayLabel;
       }
       
   }
   
   /**
    * メソッドにアノテーションを付与したレコード - マップ
    * @since 1.0
    */
   public static class MethodAnnoMapRecord {
       
       private int no;
       
       private String name;
       
       private Map<String, Boolean> dateAttended;
       
       private Point noPosition;
       
       private Point namePosition;
       
       private Map<String, Point> dateAttendedPosition;
       
       private String noLabel;
       
       private String nameLabel;
       
       private Map<String, String> dateAttendedLabel;
       
       @XlsColumn(columnName="No.")
       public int getNo() {
           return no;
       }
       
       @XlsColumn(columnName="No.")
       public void setNo(int no) {
           this.no = no;
       }
       
       @XlsColumn(columnName="氏名")
       public String getName() {
           return name;
       }
       
       @XlsColumn(columnName="氏名")
       public void setName(String name) {
           this.name = name;
       }
       
       @XlsBooleanConverter(saveAsTrue="出席", saveAsFalse="欠席")
       @XlsMapColumns(previousColumnName="氏名")
       public Map<String, Boolean> getDateAttended() {
           return dateAttended;
       }
       
       @XlsBooleanConverter(loadForTrue="出席", loadForFalse="欠席")
       @XlsMapColumns(previousColumnName="氏名")
       public void setDateAttended(Map<String, Boolean> dateAttended) {
           this.dateAttended = dateAttended;
       }
       
       @XlsIsEmpty
       public boolean isEmpty() {
           return new IsEmptyBuilder()
               .append(name)
               .append(dateAttended)
               .isEmpty();
       }
       
       public MethodAnnoMapRecord no(int no) {
           this.no = no;
           return this;
       }
       
       public MethodAnnoMapRecord name(String name) {
           this.name = name;
           return this;
       }
       
       public MethodAnnoMapRecord dateAttended(Map<String, Boolean> dateAttended) {
           this.dateAttended = dateAttended;
           return this;
       }
       
       public MethodAnnoMapRecord addDateAttended(final String key, final Boolean value) {
           if(dateAttended == null) {
               this.dateAttended = new LinkedHashMap<>();
           }
           
           this.dateAttended.put(key, value);
           
           return this;
       }
       
       public void setNoPosition(int x, int y) {
           this.noPosition = new Point(x, y);
       }
       
       public void setNamePosition(int x, int y) {
           this.namePosition = new Point(x, y);
       }
       
       public void setDateAttendedPosition(String key, int x, int y) {
           if(this.dateAttendedPosition == null) {
               this.dateAttendedPosition = new LinkedHashMap<>();
           }
           this.dateAttendedPosition.put(key, new Point(x, y));
       }
       
       public void setNoLabel(String noLabel) {
           this.noLabel = noLabel;
       }
       
       public void setNameLabel(String nameLabel) {
           this.nameLabel = nameLabel;
       }
       
       public void setDateAttendedLabel(String key, String label) {
           if(this.dateAttendedLabel == null) {
               this.dateAttendedLabel = new LinkedHashMap<>();
           }
           this.dateAttendedLabel.put(key, label);
       }
       
   }
   
   /**
    * 表のタイトルの位置の指定
    * @since 1.0
    *
    */
   @XlsSheet(name="ラベルの位置の指定")
   private static class TableLabelSheet {
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="タイトルが左", ignoreEmptyRecord=true, tableLabelAbove=false)
       private List<NormalRecord> leftRecords1;
       
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="タイトルが左（離れている）", ignoreEmptyRecord=true, tableLabelAbove=false, right=2)
       private List<NormalRecord> leftRecords2;
       
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="タイトルが上", ignoreEmptyRecord=true, tableLabelAbove=true)
       private List<NormalRecord> aboveRecords1;
       
       @XlsHint(order=4)
       @XlsVerticalRecords(tableLabel="タイトルが上（離れている）", ignoreEmptyRecord=true, tableLabelAbove=true, right=2)
       private List<NormalRecord> aboveRecords2;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public TableLabelSheet addLeft1(NormalRecord record) {
           if(leftRecords1 == null) {
               this.leftRecords1 = new ArrayList<>();
           }
           
           this.leftRecords1.add(record);
           record.no(leftRecords1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public TableLabelSheet addLeft2(NormalRecord record) {
           if(leftRecords2 == null) {
               this.leftRecords2 = new ArrayList<>();
           }
           
           this.leftRecords2.add(record);
           record.no(leftRecords2.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public TableLabelSheet addRight1(NormalRecord record) {
           if(aboveRecords1 == null) {
               this.aboveRecords1 = new ArrayList<>();
           }
           
           this.aboveRecords1.add(record);
           record.no(aboveRecords1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public TableLabelSheet addRight2(NormalRecord record) {
           if(aboveRecords2 == null) {
               this.aboveRecords2 = new ArrayList<>();
           }
           
           this.aboveRecords2.add(record);
           record.no(aboveRecords2.size());
           
           return this;
       }
       
   }
   
   /**
    * データの開始位置の指定
    */
   @XlsSheet(name="データの開始位置")
   private static class StartedDataPositionSheet {
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="データの開始位置が離れている", terminal=RecordTerminal.Border, headerRight=2,
               ignoreEmptyRecord=true, overRecord=OverRecordOperate.Copy)
       private List<DistantRecord> distantRecords;
       
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="見出しが結合", terminal=RecordTerminal.Border, headerRight=2,
               ignoreEmptyRecord=true, overRecord=OverRecordOperate.Copy)
       private List<HeaderMergedRecord> headerMergedRecords1;
       
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="見出しが結合（タイトルが上）", terminal=RecordTerminal.Border, headerRight=2,
               tableLabelAbove=true,
               ignoreEmptyRecord=true, overRecord=OverRecordOperate.Copy)
       private List<HeaderMergedRecord> headerMergedRecords2;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public StartedDataPositionSheet addDistantRecord(DistantRecord record) {
           if(distantRecords == null) {
               this.distantRecords = new ArrayList<>();
           }
           
           this.distantRecords.add(record);
           record.no(distantRecords.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public StartedDataPositionSheet addHeaderMergedRecord1(HeaderMergedRecord record) {
           if(headerMergedRecords1 == null) {
               this.headerMergedRecords1 = new ArrayList<>();
           }
           
           this.headerMergedRecords1.add(record);
           record.no(headerMergedRecords1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public StartedDataPositionSheet addHeaderMergedRecord2(HeaderMergedRecord record) {
           if(headerMergedRecords2 == null) {
               this.headerMergedRecords2 = new ArrayList<>();
           }
           
           this.headerMergedRecords2.add(record);
           record.no(headerMergedRecords2.size());
           
           return this;
       }
       
       /**
        * 
        * データ行が離れている
        */
       private static class DistantRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsConverter(defaultValue="99")
           @XlsColumn(columnName="No.", optional=true)
           private int no;
           
           @XlsColumn(columnName="氏名")
           private String name;
           
           @XlsColumn(columnName="算数")
           private int sansu;
           
           @XlsColumn(columnName="国語")
           private int kokugo;
           
           @XlsColumn(columnName="合計")
           private int sum;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "no");
           }
           
           public DistantRecord no(int no) {
               this.no = no;
               return this;
           }
           
           public DistantRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public DistantRecord sansu(int sansu) {
               this.sansu = sansu;
               return this;
           }
           
           public DistantRecord kokugo(int kokugo) {
               this.kokugo = kokugo;
               return this;
           }
           
           public DistantRecord sum(int sum) {
               this.sum = sum;
               return this;
           }
           
       }
       
       /**
        * 
        * 見出しが縦に結合
        */
       private static class HeaderMergedRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsConverter(defaultValue="99")
           @XlsColumn(columnName="No.", optional=true)
           private int no;
           
           @XlsColumn(columnName="氏名")
           private String name;
           
           @XlsColumn(columnName="テスト結果")
           private int sansu;
           
           @XlsColumn(columnName="テスト結果", headerMerged=1)
           private int kokugo;
           
           @XlsColumn(columnName="テスト結果", headerMerged=2)
           private int sum;

           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "no");
           }
           
           public HeaderMergedRecord no(int no) {
               this.no = no;
               return this;
           }
           
           public HeaderMergedRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public HeaderMergedRecord sansu(int sansu) {
               this.sansu = sansu;
               return this;
           }
           
           public HeaderMergedRecord kokugo(int kokugo) {
               this.kokugo = kokugo;
               return this;
           }
           
           public HeaderMergedRecord sum(int sum) {
               this.sum = sum;
               return this;
           }
       }
       
   }
   
   /**
    * 正規表現でラベルを一致させる
    *
    */
   @XlsSheet(name="正規表現で一致")
   private static class RegexSheet {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsVerticalRecords(tableLabel="測定結果（通常）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true)
       private List<ResultRecord> records1;
       
       @XlsVerticalRecords(tableLabel="/測定結果\\[.+\\]/", terminal=RecordTerminal.Border, ignoreEmptyRecord=true)
       private List<ResultRecord> records2;
       
       @XlsVerticalRecords(tableLabel="測定結果（見出しが正規表現）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true)
       private List<ResultRecord> records3;
       
       @XlsVerticalRecords(tableLabel="測定結果（終端が正規表現）", terminal=RecordTerminal.Border, ignoreEmptyRecord=true, terminateLabel="/.*合計.*/")
       private List<ResultRecord> records4;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RegexSheet addRecord1(ResultRecord record) {
           if(records1 == null) {
               this.records1 = new ArrayList<>();
           }
           
           this.records1.add(record);
           record.no(records1.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RegexSheet addRecord2(ResultRecord record) {
           if(records2 == null) {
               this.records2 = new ArrayList<>();
           }
           
           this.records2.add(record);
           record.no(records2.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RegexSheet addRecord3(ResultRecord record) {
           if(records3 == null) {
               this.records3 = new ArrayList<>();
           }
           
           this.records3.add(record);
           record.no(records3.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public RegexSheet addRecord4(ResultRecord record) {
           if(records4 == null) {
               this.records4 = new ArrayList<>();
           }
           
           this.records4.add(record);
           record.no(records4.size());
           
           return this;
       }
       
       private static class ResultRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="No.", optional=true)
           private int no;
           
           @XlsColumn(columnName="/名前.*/")
           private String name;
           
           @XlsMapColumns(previousColumnName="/名前.*/", nextColumnName="/備考.*/")
           private Map<String, Integer> resultMap;
           
           @XlsColumn(columnName="/備考.*/")
           private String comment;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "no");
           }
           
           public ResultRecord no(int no) {
               this.no = no;
               return this;
           }
           
           public ResultRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public ResultRecord result(String key, Integer score) {
               if(resultMap == null) {
                   this.resultMap = new LinkedHashMap<>();
               }
               
               this.resultMap.put(key, score);
               return this;
           }
           
           public ResultRecord comment(String comment) {
               this.comment = comment;
               return this;
           }
       }
       
   }
   
   @XlsSheet(name="ネストした表")
   private static class NestedSheet {
       
       private Map<String, Point> positions;
       
       private Map<String, String> labels;
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="通常の表", overRecord=OverRecordOperate.Copy)
       private List<LargeRecord> largeRecords1;
       
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="空のレコードがある表", terminal=RecordTerminal.Border, 
               overRecord=OverRecordOperate.Copy, remainedRecord=RemainedRecordOperate.Clear, ignoreEmptyRecord=true)
       private List<LargeRecord> largeRecords2;
       
       @XlsHint(order=3)
       @XlsVerticalRecords(tableLabel="見出しが結合している表", terminal=RecordTerminal.Border, 
               overRecord=OverRecordOperate.Copy, remainedRecord=RemainedRecordOperate.Clear, ignoreEmptyRecord=true, headerRight=2)
       private List<HeaderMergedLargeRecord> largeRecords3;
       
       @XlsHint(order=4)
       @XlsVerticalRecords(tableLabel="1対1のネスト", overRecord=OverRecordOperate.Copy, ignoreEmptyRecord=true)
       private List<OneToOneRecord> oneToOneRecords;
       
       public NestedSheet addRecord1(LargeRecord record) {
           if(largeRecords1 == null) {
               this.largeRecords1 = new ArrayList<>();
           }
           
           this.largeRecords1.add(record);
           
           return this;
       }
       
       public NestedSheet addRecord2(LargeRecord record) {
           if(largeRecords2 == null) {
               this.largeRecords2 = new ArrayList<>();
           }
           
           this.largeRecords2.add(record);
           
           return this;
       }
       
       public NestedSheet addRecord3(HeaderMergedLargeRecord record) {
           if(largeRecords3 == null) {
               this.largeRecords3 = new ArrayList<>();
           }
           
           this.largeRecords3.add(record);
           
           return this;
       }
       
       public NestedSheet addRecord(OneToOneRecord record) {
           if(oneToOneRecords == null) {
               this.oneToOneRecords = new ArrayList<>();
           }
           
           this.oneToOneRecords.add(record);
           
           return this;
       }
       
       public void printRecord1() {
           
           System.out.printf("----- ▽▽▽▽table :: [%s] ▽▽▽▽------\n", labels.get("largeRecords1"));
           
           if(largeRecords1 == null) {
               return;
           }
           
           for(LargeRecord largeRecord : largeRecords1) {
               largeRecord.printRecord();
           }
           
           System.out.printf("----- △△△△table :: [%s] △△△△------\n", labels.get("largeRecords1"));
           
       }
       
       public void printRecord2() {
           
           System.out.printf("----- ▽▽▽▽table :: [%s] ▽▽▽▽------\n", labels.get("largeRecords2"));
           
           if(largeRecords2 == null) {
               return;
           }
           
           for(LargeRecord largeRecord : largeRecords2) {
               largeRecord.printRecord();
           }
           
           System.out.printf("----- △△△△table :: [%s] △△△△------\n", labels.get("largeRecords2"));

       }
       
       public void printRecord3() {
           
           System.out.printf("----- ▽▽▽▽table :: [%s] ▽▽▽▽------\n", labels.get("largeRecords3"));
           
           if(largeRecords3 == null) {
               return;
           }
           
           for(HeaderMergedLargeRecord largeRecord : largeRecords3) {
               largeRecord.printRecord();
           }
           
           System.out.printf("----- △△△△table :: [%s] △△△△------\n", labels.get("largeRecords3"));

       }
       
       private static class LargeRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="大分類")
           private String largeName;
           
           @XlsColumn(columnName="説明（大分類）")
           private String largeDescription;
           
           @XlsNestedRecords
           private List<MiddleRecord> middleRecords;
           
           public LargeRecord addRecord(MiddleRecord record) {
               if(middleRecords == null) {
                   this.middleRecords = new ArrayList<>();
               }
               
               this.middleRecords.add(record);
               
               return this;
           }
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "middleRecords");
           }
           
           public void printRecord() {
               
               System.out.printf("◆%s::%s (%s::%s), \n",
                       largeName, POIUtils.formatCellAddress(positions.get("largeName")),
                       largeDescription, POIUtils.formatCellAddress(positions.get("largeDescription")));
               
               if(middleRecords == null) {
                   return;
               }
               
               for(MiddleRecord middleRecord : middleRecords) {
                   middleRecord.printRecord();
               }
           }
           
           public LargeRecord largeName(String largeName) {
               this.largeName = largeName;
               return this;
           }
           
           public LargeRecord largeDescription(String largeDescription) {
               this.largeDescription = largeDescription;
               return this;
           }
       }
       
       private static class MiddleRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="中分類")
           private String middleName;
           
           @XlsColumn(columnName="説明（中分類）")
           private String middleDescription;
           
           @XlsNestedRecords
           private SmallRecord[] smallRecords;
           
           public MiddleRecord addRecord(SmallRecord record) {
               
               final List<SmallRecord> list;
               if(smallRecords == null) {
                   list = new ArrayList<>();
               } else {
                   list = new ArrayList<>(Arrays.asList(smallRecords));
               }
               
               list.add(record);
               
               this.smallRecords = list.toArray(new SmallRecord[list.size()]);
               
               return this;
           }
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "smallRecords");
           }
           
           public void printRecord() {
               
               System.out.printf("    ○%s::%s (%s::%s)\n",
                       middleName, POIUtils.formatCellAddress(positions.get("middleName")),
                       middleDescription, POIUtils.formatCellAddress(positions.get("middleDescription")));
               
               if(smallRecords == null) {
                   return;
               }
               
               for(SmallRecord smallRecord : smallRecords) {
                   smallRecord.printRecord();
               }
           }
           
           public MiddleRecord middleName(String middleName) {
               this.middleName = middleName;
               return this;
           }
           
           public MiddleRecord middleDescription(String middleDescription) {
               this.middleDescription = middleDescription;
               return this;
           }
       }
       
       private static class SmallRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="項目")
           private String name;
           
           @XlsColumn(columnName="値")
           private String value;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels");
           }
           
           public void printRecord() {
               System.out.printf("        ・%s::%s=%s::%s\n",
                       name, POIUtils.formatCellAddress(positions.get("name")),
                       value, POIUtils.formatCellAddress(positions.get("value")));
           }
           
           public SmallRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public SmallRecord value(String value) {
               this.value = value;
               return this;
           }
       }
       
       private static class HeaderMergedLargeRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="大分類")
           private String largeName;
           
           @XlsColumn(columnName="大分類", headerMerged=1)
           private String largeDescription;
           
           @XlsNestedRecords
           private List<HeaderMergedMiddleRecord> middleRecords;
           
           public HeaderMergedLargeRecord addRecord(HeaderMergedMiddleRecord record) {
               if(middleRecords == null) {
                   this.middleRecords = new ArrayList<>();
               }
               
               this.middleRecords.add(record);
               
               return this;
           }
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "middleRecords");
           }
           
           public void printRecord() {
               
               System.out.printf("◆%s::%s (%s::%s), \n",
                       largeName, POIUtils.formatCellAddress(positions.get("largeName")),
                       largeDescription, POIUtils.formatCellAddress(positions.get("largeDescription")));
               
               if(middleRecords == null) {
                   return;
               }
               
               for(HeaderMergedMiddleRecord middleRecord : middleRecords) {
                   middleRecord.printRecord();
               }
           }
           
           public HeaderMergedLargeRecord largeName(String largeName) {
               this.largeName = largeName;
               return this;
           }
           
           public HeaderMergedLargeRecord largeDescription(String largeDescription) {
               this.largeDescription = largeDescription;
               return this;
           }
       }
       
       private static class HeaderMergedMiddleRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="中分類")
           private String middleName;
           
           @XlsColumn(columnName="中分類", headerMerged=1)
           private String middleDescription;
           
           @XlsNestedRecords
           private HeaderMergedSmallRecord[] smallRecords;
           
           public HeaderMergedMiddleRecord addRecord(HeaderMergedSmallRecord record) {
               
               final List<HeaderMergedSmallRecord> list;
               if(smallRecords == null) {
                   list = new ArrayList<>();
               } else {
                   list = new ArrayList<>(Arrays.asList(smallRecords));
               }
               
               list.add(record);
               
               this.smallRecords = list.toArray(new HeaderMergedSmallRecord[list.size()]);
               
               return this;
           }
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels", "smallRecords");
           }
           
           public void printRecord() {
               
               System.out.printf("    ○%s::%s (%s::%s)\n",
                       middleName, POIUtils.formatCellAddress(positions.get("middleName")),
                       middleDescription, POIUtils.formatCellAddress(positions.get("middleDescription")));
               
               if(smallRecords == null) {
                   return;
               }
               
               for(HeaderMergedSmallRecord smallRecord : smallRecords) {
                   smallRecord.printRecord();
               }
           }
           
           public HeaderMergedMiddleRecord middleName(String middleName) {
               this.middleName = middleName;
               return this;
           }
           
           public HeaderMergedMiddleRecord middleDescription(String middleDescription) {
               this.middleDescription = middleDescription;
               return this;
           }
       }
       
       private static class HeaderMergedSmallRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="詳細")
           private String name;
           
           @XlsColumn(columnName="詳細", headerMerged=1)
           private String value;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels");
           }
           
           public void printRecord() {
               System.out.printf("        ・%s::%s=%s::%s\n",
                       name, POIUtils.formatCellAddress(positions.get("name")),
                       value, POIUtils.formatCellAddress(positions.get("value")));
           }
           
           public HeaderMergedSmallRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public HeaderMergedSmallRecord value(String value) {
               this.value = value;
               return this;
           }
       }
       
       private static class OneToOneRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="No.")
           private int no;
           
           @XlsColumn(columnName="クラス", merged=true)
           private String className;
           
           @XlsColumn(columnName="氏名")
           private String name;
           
           @XlsColumn(columnName="生年月日")
           @XlsDateConverter(excelPattern="yyyy\"年\"m\"月\"d\"日\";@")
           private Date birthday;
           
           @XlsNestedRecords
           private ResultRecord result;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels");
           }
           
           public OneToOneRecord no(int no) {
               this.no = no;
               return this;
           }
           
           public OneToOneRecord className(String className) {
               this.className = className;
               return this;
           }
           
           public OneToOneRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public OneToOneRecord birthday(Date birthday) {
               this.birthday = birthday;
               return this;
           }
           
           public OneToOneRecord result(ResultRecord result) {
               this.result = result;
               return this;
           }
       }
       
       private static class ResultRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           @XlsColumn(columnName="算数")
           private int sansu;
           
           @XlsColumn(columnName="国語")
           private int kokugo;
           
           @XlsColumn(columnName="合計")
           private int sum;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels");
           }
           
           public ResultRecord sansu(int sansu) {
               this.sansu = sansu;
               return this;
           }
           
           public ResultRecord kokugo(int kokugo) {
               this.kokugo = kokugo;
               return this;
           }
           
           public ResultRecord sum(int sum) {
               this.sum = sum;
               return this;
           }
           
       }
       
   }
   
   @XlsSheet(name="数式を指定")
   private static class FormulaSheet {
       
       @XlsHint(order=1)
       @XlsVerticalRecords(tableLabel="成績一覧", headerRight=2, terminal=RecordTerminal.Border, overRecord=OverRecordOperate.Copy)
       private List<GradeRecord> gradeRecrods;
       
       @XlsHint(order=2)
       @XlsVerticalRecords(tableLabel="出欠確認", terminal=RecordTerminal.Border, overRecord=OverRecordOperate.Copy)
       private List<EntryRecord> entryRecords;
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public FormulaSheet add(GradeRecord record) {
           if(gradeRecrods == null) {
               this.gradeRecrods = new ArrayList<>();
           }
           
           // 親をたどれるようにする
           record.parent = this;
           
           this.gradeRecrods.add(record);
           record.no(gradeRecrods.size());
           
           return this;
       }
       
       /**
        * noを自動的に付与する。
        * @param record
        * @return 自身のインスタンス
        */
       public FormulaSheet add(EntryRecord record) {
           if(entryRecords == null) {
               this.entryRecords = new ArrayList<>();
           }
           
           // 親をたどれるようにする
           record.parent = this;
           
           this.entryRecords.add(record);
           record.no(entryRecords.size());
           
           return this;
       }
       
       private static class GradeRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           private FormulaSheet parent;
           
           @XlsColumn(columnName="No.")
           private int no;
           
           @XlsColumn(columnName="氏名")
           private String name;
           
           @XlsColumn(columnName="氏名", headerMerged=1)
           @XlsFormula(methodName="getAverageFormula")
           private Integer sansu;
           
           @XlsColumn(columnName="氏名", headerMerged=2)
           @XlsFormula(methodName="getAverageFormula")
           private Integer kokugo;
           
           @XlsColumn(columnName="氏名", headerMerged=3)
           @XlsFormula("SUM({columnAlpha}6:{columnAlpha}7)")
           private Integer sum;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels");
           }
           
           public GradeRecord no(int no) {
               this.no = no;
               return this;
           }
           
           public GradeRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public GradeRecord sansu(int sansu) {
               this.sansu = sansu;
               return this;
           }
           
           public GradeRecord kokugo(int kokugo) {
               this.kokugo = kokugo;
               return this;
           }
           
           public String getAverageFormula(final Point address) {
               
               final int rowNumber = address.y + 1;
               
               final String startColumnAlpha = CellReference.convertNumToColString(address.x + 1 - parent.gradeRecrods.size());
               final String endColumnAlpha = CellReference.convertNumToColString(address.x - 1);
               
               String formula =  String.format("AVERAGE(%s%d:%s%d)", startColumnAlpha, rowNumber, endColumnAlpha, rowNumber);
               return formula;
               
           }
           
       }
       
       private static class EntryRecord {
           
           private Map<String, Point> positions;
           
           private Map<String, String> labels;
           
           private FormulaSheet parent;
           
           @XlsColumn(columnName="No.")
           private int no;
           
           @XlsColumn(columnName="氏名")
           private String name;
           
           @XlsMapColumns(previousColumnName="氏名", nextColumnName="備考")
           @XlsFormula("${rowNumber == 16 ? 'COUNTIF(' + columnAlpha + '13' + ':' + columnAlpha + '15' + ', \"出席\")' : ''}")
           private Map<String, String> dateAttended;
           
           @XlsColumn(columnName="備考")
           private String comment;
           
           @XlsIsEmpty
           public boolean isEmpty() {
               return IsEmptyBuilder.reflectionIsEmpty(this, "positions", "labels");
           }
           
           public EntryRecord no(int no) {
               this.no = no;
               return this;
           }
           
           public EntryRecord name(String name) {
               this.name = name;
               return this;
           }
           
           public EntryRecord dateAttended(Map<String, String> dateAttended) {
               this.dateAttended = dateAttended;
               return this;
           }
           
           public EntryRecord addDateAttended(final String key, final String value) {
               if(dateAttended == null) {
                   this.dateAttended = new LinkedHashMap<>();
               }
               
               this.dateAttended.put(key, value);
               
               return this;
           }
           
           public EntryRecord comment(String comment) {
               this.comment = comment;
               return this;
           }
           
           public String getCountFormula(final Point address) {
               
               final int rowNumber = address.y + 1;
               if(rowNumber != 16) {
                   return null;
               }
               final String columnAlpha = CellReference.convertNumToColString(address.x);
               
               
               String formula =  String.format("COUNTIF(%s13:%s15, \"出席\")", columnAlpha, columnAlpha);
               return formula;
           }
           
       }
   }
   
   
}
