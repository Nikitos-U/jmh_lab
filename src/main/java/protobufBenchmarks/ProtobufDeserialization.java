package protobufBenchmarks;

import com.google.protobuf.InvalidProtocolBufferException;
import data.LabStateDTO;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;



public class ProtobufDeserialization {
    private static final LabStateDTO.LabState.Builder labStateBuilder = LabStateDTO.LabState.newBuilder()
            .setCode("Here is the code")
            .setResults("Check in results folder")
            .setFunnyJokes("ez");

    private static final LabStateDTO.LabState labState = labStateBuilder.build();
    private static final byte[] protoData = labState.toByteArray();

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void protobufDeserializationThroughput() throws InvalidProtocolBufferException {
        LabStateDTO.LabState result = LabStateDTO.LabState.parseFrom(protoData);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void protobufDeserializationAvgTime() throws InvalidProtocolBufferException {
        LabStateDTO.LabState result = LabStateDTO.LabState.parseFrom(protoData);
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