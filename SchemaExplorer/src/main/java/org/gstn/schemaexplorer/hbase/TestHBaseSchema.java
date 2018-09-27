/*******************************************************************************
 * Copyright 2018 Goods And Services Tax Network
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.gstn.schemaexplorer.hbase;

import java.io.IOException;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.gstn.schemaexplorer.hbasegrammar.HBaseTableGrammarLexer;
import org.gstn.schemaexplorer.hbasegrammar.HBaseTableGrammarParser;

class TestHBaseSchema {

    public static void main(String [] args) throws IOException {
        System.out.println("hello... testing antlr parser");

        Scanner sc = new Scanner(System.in);
        
        String inputStr = sc.nextLine();
        ANTLRInputStream input = new ANTLRInputStream(inputStr); 

        HBaseTableGrammarLexer lexer = new HBaseTableGrammarLexer(input);
        HBaseTableIR hbaseTable = new HBaseTableIR();

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        HBaseTableGrammarParser parser = new HBaseTableGrammarParser(tokens);
        HBaseTableListener listener = new HBaseTableListener(hbaseTable);

        ParseTree tree = parser.input();
        System.out.println(tree.toStringTree(parser));

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        
        sc.close();
        
    }
}
