package org.springframework.jenkins.cloud.common


import groovy.transform.CompileStatic

/**
 * Contains lists of jobs. By default we create the jobs and views in the following way
 *
 * ${project-name}-${branch-name}-ci
 *
 * e.g.
 *
 * spring-cloud-sleuth-master-ci
 * spring-cloud-netflix-1.0.x-ci
 *
 * @author Marcin Grzejszczak
 */
@CompileStatic
class AllCloudJobs {
	/**
	 * List of all Spring Cloud jobs. This list will be used to create the boot compatibility builds
	 * and will serve as basis for the default jobs
	 */
	public static final List<String> ALL_JOBS = ['spring-cloud-sleuth', 'spring-cloud-netflix', 'spring-cloud-zookeeper', 'spring-cloud-consul',
												 'spring-cloud-bus', 'spring-cloud-commons', 'spring-cloud-security', 'spring-cloud-config',
												 'spring-cloud-cloudfoundry', 'spring-cloud-aws', 'spring-cloud-build',
												 'spring-cloud-cli', 'spring-cloud-contract', 'spring-cloud-vault', 'spring-cloud-gateway',
												 'spring-cloud-openfeign', 'spring-cloud-function', 'spring-cloud-gcp']
	/**
	 * List of all Spring Cloud Stream jobs for the releaser. This list will be used to create the boot compatibility builds
	 * and will serve as basis for the default jobs
	 */
	public static final List<String> ALL_STREAM_JOBS_FOR_RELEASER = ["spring-cloud-stream",
																	 "spring-cloud-stream-binder-rabbit",
																	 "spring-cloud-stream-binder-kafka"]

	/**
	 * List of all single project jobs to be used by the releaser
	 */
	public static final List<String> ALL_RELEASER_JOBS = ALL_JOBS
	/**
	 * Some projects need to have the test report generation skipped (since they have no tests).
	 */
	public static final List<String> JOBS_WITHOUT_TESTS = ['spring-cloud-build', 'spring-cloud-release']

	/**
	 * Projects from this list will have the jobs with report generation
	 */
	public static final List<String> ALL_JOBS_WITH_TESTS = ALL_JOBS - JOBS_WITHOUT_TESTS

	/**
	 * Apart from projects containing libraries we also do have the samples. Currently the list
	 * is not really impressive but at least we have a hook for that
	 */
	public static final List<String> ALL_SAMPLES_JOBS = ['tests']

	/**
	 * There are some projects that require custom setup / teardown. Provide the list here.
	 * That way the default CI jobs will not get generated. You can see that there are duplicates
	 * in this list and {@link AllCloudJobs#ALL_JOBS}. That's intentional cause we need the list
	 * of names of all jobs that we have in the organization. Since some jobs are custom
	 * we will have custom implementations. Check out {@link org.springframework.jenkins.cloud.compatibility.ManualBootCompatibilityBuildMaker}
	 * for more info.
	 */
	public static final List<String> CUSTOM_BUILD_JOBS = ['spring-cloud-consul', 'spring-cloud-build',
														  'spring-cloud-contract', 'spring-cloud-netflix', 'spring-cloud-vault']

	/**
	 * {@link AllCloudJobs#ALL_DEFAULT_JOBS} creates jobs for master branch. Sometimes you need other branches.
	 * That's why it's enough to provide the name of the project and the list of branches to build
	 */
	public static final Map<String, List<String>> JOBS_WITH_BRANCHES = ['spring-cloud-sleuth'      : ['1.3.x', '2.0.x', '2.1.x'],
																		'spring-cloud-cli'         : ['1.0.x', '1.1.x', '2.0.x'],
																		'spring-cloud-gcp'         : ['1.0.x', '1.1.x'],
																		'spring-cloud-commons'     : ['1.2.x', '1.3.x', '2.0.x', '2.1.x'],
																		'spring-cloud-contract'    : ['2.0.x', '1.2.x'],
																		'spring-cloud-config'      : ['1.3.x', '1.4.x', '2.0.x', '2.1.x'],
																		'spring-cloud-netflix'     : ['1.3.x', '1.4.x', '2.0.x', '2.1.x'],
																		'spring-cloud-consul'      : ['1.3.x', '1.2.x', '2.0.x', '2.1.x'],
																		'spring-cloud-zookeeper'   : ['1.1.x', '1.2.x', '2.0.x'],
																		'spring-cloud-bus'         : ['1.2.x', '1.3.x', '2.0.x', '2.1.x'],
																		'spring-cloud-build'       : ['1.3.x', '2.0.x', '2.1.x'],
																		'spring-cloud-aws'         : ['1.1.x', '1.2.x', '2.0.x', '2.1.x'],
																		'spring-cloud-gateway'     : ['1.0.x', '2.0.x', '2.1.x'],
																		'spring-cloud-security'    : ['1.1.x', '1.2.x', '2.0.x', '2.1.x'],
																		'spring-cloud-vault'       : ['1.1.x', '2.0.x', '2.1.x'],
																		'spring-cloud-cloudfoundry': ['1.1.x', '2.0.x', '2.1.x'],
																		'spring-cloud-openfeign'   : ['2.0.x', '2.1.x']]

	/**
	 * List of default jobs. Default means that `./mvnw clean deploy` will be executed to publish artifacts
	 * and `./mvwn clean install -Pdocs` + `gh-pages.sh` script will be executed to publish new docs.
	 * The docs will get published only for master.
	 */
	public static final List<String> ALL_DEFAULT_JOBS = ALL_JOBS - CUSTOM_BUILD_JOBS

	/**
	 * List of jobs that don't need boot compatibility tests.
	 */
	public static final List<String> JOBS_WITHOUT_BOOT_COMPATIBILITY = ['spring-cloud-cli']

	/**
	 * List of all jobs that need to be executed when doing compatibility builds against
	 * latest version of boot. This is a list of names of jobs. The proper implementations
	 * like {@link org.springframework.jenkins.cloud.compatibility.ManualBootCompatibilityBuildMaker} or
	 * {@link org.springframework.jenkins.cloud.compatibility.BootCompatibilityBuildMaker} will try
	 * to execute the jobs having those predefined names (with a proper suffix). It's up to
	 * the implementors to ensure that those jobs really exist.
	 */
	public static final List<String> BOOT_COMPATIBILITY_BUILD_JOBS = ALL_JOBS + ALL_SAMPLES_JOBS - JOBS_WITHOUT_BOOT_COMPATIBILITY

	/**
	 * Map of projects to email addresses. If a project name is matched, the list of email
	 * addresses will be applied to the project.
	 */
	public static final Map<String, List<String>> EMAIL_NOTIFICATIONS = [
			"spring-cloud-gcp": ["meltsufin@google.com", "abilan@pivotal.io"]
	]

}
