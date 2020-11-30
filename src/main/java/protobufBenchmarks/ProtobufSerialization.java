package protobufBenchmarks;

import data.LabStateDTO;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static com.googlecode.protobuf.format.XmlFormat.*;


public class ProtobufSerialization {

    private static final LabStateDTO.LabState.Builder labStateBuilder = LabStateDTO.LabState.newBuilder()
            .setCode("Here is the code")
            .setResults("Check in results folder")
            .setFunnyJokes("ez");

    private static final LabStateDTO.LabState labState = labStateBuilder.build();

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void protobufSerializationThroughput() {
        String res = printToString(labState);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void protobufSerializationAvgTime() {
        String res = printToString(labState);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ProtobufSerialization.class.getSimpleName())
                .threads(4)
                .warmupIterations(5)
                .measurementIterations(7)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}